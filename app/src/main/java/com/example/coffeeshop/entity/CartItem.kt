package com.example.coffeeshop.entity


data class CartItem (var name: String, var price: Int, var quantity: Int, var size: Size) {
    fun reset() {
        name = ""
        price = 0
        quantity = 0
        size = Size.S
    }
}