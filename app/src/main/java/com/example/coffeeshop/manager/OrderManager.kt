package com.example.coffeeshop.manager

import com.example.coffeeshop.entity.Order

object OrderManager {
    private val orderList: MutableList<Order> = mutableListOf()

    fun addOrder(item: Order) {
        val orderItem = Order(item.userName, item.itemName, item.size, item.quantity, item.price)
        orderList.add(orderItem)
    }

    fun getOrderList(): List<Order> {
        return orderList.toList()
    }
}