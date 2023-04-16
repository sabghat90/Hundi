package com.sabghat.hundi.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface OrderDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertOrder(order: Order)

    @Update
    fun updateOrder(order: Order)

    @Query("SELECT * FROM orders ORDER BY id DESC")
    fun getAllOrders(): LiveData<List<Order>>
}