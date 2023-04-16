package com.sabghat.hundi.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.*

@Entity(tableName = "orders")
@Parcelize
data class Order(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val receiverName: String,
    val transferAmount: Int,
    val commission: Int,
    val transferDate: String,
) : Parcelable