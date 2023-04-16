package com.sabghat.hundi.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "orders")
data class Order(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val receiverName: String,
    val transferAmount: Int,
    val transferDate: Date,
)