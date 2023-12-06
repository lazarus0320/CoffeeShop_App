package com.example.coffeeshop.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.coffeeshop.R
import com.example.coffeeshop.entity.CartItem
import com.example.coffeeshop.entity.Size

class CartHistoryFragment : Fragment() {

    private lateinit var totalTextView: TextView
    private lateinit var checkoutButton: Button
    private lateinit var cartItemsContainer: LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.cart_history, container, false)

        cartItemsContainer = view.findViewById(R.id.cart_items_container)
        val cartList = CartManager.getCartItemList()

        totalTextView = view.findViewById(R.id.total_price_textview)
        checkoutButton = view.findViewById(R.id.checkout_button)

        for (cartItem in cartList) {
            addCartItemView(cartItem)
            System.out.println(cartItem)
        }

        updateTotalPrice(cartList)

        return view
    }

    @SuppressLint("MissingInflatedId", "SetTextI18n")
    private fun addCartItemView(cartItem: CartItem) {
        val cartItemView = LayoutInflater.from(context).inflate(R.layout.cart_item_layout, cartItemsContainer, false)

        val nameTextView: TextView = cartItemView.findViewById(R.id.item_name_textview)
        nameTextView.text = "이름: ${cartItem.name}"

        val sizeTextView: TextView = cartItemView.findViewById(R.id.item_size_textview)
        sizeTextView.text = "사이즈: ${getSizeString(cartItem.size)}"

        val quantityTextView: TextView = cartItemView.findViewById(R.id.item_quantity_textview)
        quantityTextView.text = "${cartItem.quantity}"

        val priceTextView: TextView = cartItemView.findViewById(R.id.item_price_textview)
        val sizeValue = getSizeInt(cartItem.size)
        priceTextView.text = "가격: ${(cartItem.price + sizeValue) * cartItem.quantity}원"

        val decreaseButton: Button = cartItemView.findViewById(R.id.decrease_quantity_button)
        decreaseButton.setOnClickListener {
            if (cartItem.quantity > 1) {
                val newQuantity = cartItem.quantity - 1
                cartItem.quantity = newQuantity
                quantityTextView.text = newQuantity.toString()
                CartManager.updateQuantity(cartItem, newQuantity)
                updateTotalPrice(CartManager.getCartItemList())
                priceTextView.text = "가격: ${(cartItem.price + sizeValue) * cartItem.quantity}원"
            }
        }

        val increaseButton: Button = cartItemView.findViewById(R.id.increase_quantity_button)
        increaseButton.setOnClickListener {
            val newQuantity = cartItem.quantity + 1
            cartItem.quantity = newQuantity
            quantityTextView.text = newQuantity.toString()
            CartManager.updateQuantity(cartItem, newQuantity)
            updateTotalPrice(CartManager.getCartItemList())
            priceTextView.text = "가격: ${(cartItem.price + sizeValue) * cartItem.quantity}원"
        }

        val removeButton: Button = cartItemView.findViewById(R.id.remove_item_button)
        removeButton.setOnClickListener {
            CartManager.removeCartItem(cartItem)
            cartItemsContainer.removeView(cartItemView)
            updateTotalPrice(CartManager.getCartItemList())
        }


        cartItemsContainer.addView(cartItemView)
    }

    private fun getSizeString(size: Size): String {
        return when (size) {
            Size.S -> "S"
            Size.M -> "M"
            Size.L -> "L"
        }
    }

    private fun getSizeInt(size: Size): Int {
        return when (size) {
            Size.S -> 0
            Size.M -> 500
            Size.L -> 1000
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateTotalPrice(cartList: List<CartItem>) {
        val totalPrice = cartList.sumOf { (it.price + getSizeInt(it.size)) * it.quantity }
        totalTextView.text = "총 가격: $totalPrice 원"
    }
}
