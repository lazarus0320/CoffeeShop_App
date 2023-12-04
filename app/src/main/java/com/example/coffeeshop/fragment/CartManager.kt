package com.example.coffeeshop.fragment

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


}