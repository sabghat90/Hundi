package com.sabghat.hundi.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.sabghat.hundi.data.Order
import com.sabghat.hundi.databinding.OrderItemBinding

class OrderListingAdapter(
    val onClicked: (Int, Order) -> Unit
) : RecyclerView.Adapter<OrderListingAdapter.OrderListingViewHolder>() {

    var orders = emptyList<Order>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderListingViewHolder {
        val binding = OrderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrderListingViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return orders.size
    }

    override fun onBindViewHolder(holder: OrderListingViewHolder, position: Int) {
        val order = orders[position]
        holder.bind(order, position)
    }

    inner class OrderListingViewHolder(private val binding: OrderItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(order: Order, position: Int) {
            binding.tvReceiverName.text = order.receiverName
            binding.tvBalance.text = order.transferAmount.toString()
            binding.tvOrderId.text = order.id.toString()

            binding.orderCard.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onClicked(position, order)
                }
            }
        }

        }
    }
