package com.example.coffeeshop.entity

data class Order (
    val userName: String,
    val itemName: String,
    val size: String,
    val quantity: Int,
    val price: Int
    )