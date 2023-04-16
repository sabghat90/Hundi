package com.sabghat.hundi.data

import androidx.lifecycle.LiveData

class OrderRepository(private val orderDao: OrderDao) {

    val getAllOrders : LiveData<List<Order>> = orderDao.getAllOrders()

    suspend fun insertOrder(order: Order) {
        orderDao.insertOrder(order)
    }
}