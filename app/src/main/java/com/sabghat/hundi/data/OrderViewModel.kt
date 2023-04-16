package com.sabghat.hundi.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OrderViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: OrderRepository
    private val getAllOrders: LiveData<List<Order>>

    init {
        val orderDao = OrderDatabase.getDatabase(application).orderDao
        repository = OrderRepository(orderDao)
        getAllOrders = repository.getAllOrders
    }

    fun insertOrder(order: Order) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertOrder(order)
        }
    }
}