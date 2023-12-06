package com.example.coffeeshop.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.coffeeshop.R
import com.example.coffeeshop.entity.CartItem
import com.example.coffeeshop.entity.Size

class CartHistoryFragment: Fragment() {

    private lateinit var totalTextView: TextView
    private lateinit var checkoutButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.cart_history, container, false)
        val cartTable: TableLayout = view.findViewById(R.id.cart_table)

        val cartList = CartManager.getCartItemList()

        totalTextView = view.findViewById(R.id.total_price_textview)
        checkoutButton = view.findViewById(R.id.checkout_button)

        for (cartItem in cartList) {
            addCartToTable(cartTable, cartItem)
        }

        updateTotalPrice(cartList)

        return view
    }

    @SuppressLint("SetTextI18n")
    private fun addCartToTable(tableLayout: TableLayout, cartItem: CartItem) {
        val row = TableRow(context)
        val layoutParams = TableRow.LayoutParams(
            TableRow.LayoutParams.MATCH_PARENT,
            TableRow.LayoutParams.WRAP_CONTENT
        )

        row.layoutParams = layoutParams

        val nameTextView = TextView(context)
        nameTextView.text = cartItem.name
        nameTextView.layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT,  1.3f)
        nameTextView.gravity = Gravity.START

        val sizeTextView = TextView(context)
        sizeTextView.text = getSizeString(cartItem.size)
        sizeTextView.layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.8f)
        sizeTextView.gravity = Gravity.CENTER

        val quantityTextView = TextView(context)
        quantityTextView.text = cartItem.quantity.toString()
        quantityTextView.layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.8f)
        quantityTextView.gravity = Gravity.CENTER

        var sizeValue = getSizeInt(cartItem.size)

        val priceTextView = TextView(context)
        priceTextView.text = "${(cartItem.price + sizeValue) * cartItem.quantity}원"
        priceTextView.layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1.2f)
        priceTextView.gravity = Gravity.END

        row.addView(nameTextView)
        row.addView(sizeTextView)
        row.addView(quantityTextView)
        row.addView(priceTextView)

        tableLayout.addView(row)
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
        val totalPrice = cartList.sumOf { (it.price + getSizeInt(it.size) ) * it.quantity }
        totalTextView.text = "총 가격: $totalPrice 원"
    }
}