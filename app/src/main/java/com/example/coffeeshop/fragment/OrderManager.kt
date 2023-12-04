package com.example.coffeeshop.fragment

import com.example.coffeeshop.entity.CoffeeTeaItem
import com.example.coffeeshop.entity.OrderItem

object OrderManager {
    private val orderList: MutableList<OrderItem> = mutableListOf()

    fun addOrder(item: CoffeeTeaItem, quantity: Int) {
        val orderItem = OrderItem(item.name, item.price, quantity, item.size)
        orderList.add(orderItem)
    }

    fun getOrderList(): List<OrderItem> {
        return orderList.toList()
    }


}