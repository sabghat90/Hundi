package com.sabghat.hundi.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface OrderDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertOrder(order: Order)

    @Query("SELECT * FROM orders ORDER BY id DESC")
    fun getAllOrders(): LiveData<List<Order>>
}