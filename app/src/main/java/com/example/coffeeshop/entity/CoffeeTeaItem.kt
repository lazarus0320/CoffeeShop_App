package com.example.coffeeshop.entity

data class CoffeeTeaItem(
    val imageResource: Int, val name: String, var price: Int, var size: Size = Size.S)