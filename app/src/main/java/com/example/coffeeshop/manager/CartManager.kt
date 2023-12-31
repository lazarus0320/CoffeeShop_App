package com.example.coffeeshop.manager

import com.example.coffeeshop.entity.CoffeeTeaItem
import com.example.coffeeshop.entity.CartItem

object CartManager {
    private val cartItemList: MutableList<CartItem> = mutableListOf()

    fun addCartItem(item: CoffeeTeaItem, quantity: Int) {
        val cartItem = CartItem(item.name, item.price, quantity, item.size)
        cartItemList.add(cartItem)
    }

    fun getCartItemList(): List<CartItem> {
        return cartItemList.toList()
    }

    fun updateQuantity(cartItem: CartItem, newQuantity: Int) {
        cartItemList.find { it == cartItem }?.quantity = newQuantity
    }

    fun removeCartItem(cartItem: CartItem) {
        cartItemList.remove(cartItem)
    }

    fun clearCart() {
        cartItemList.forEach { it.reset() }
        cartItemList.clear()
    }

}