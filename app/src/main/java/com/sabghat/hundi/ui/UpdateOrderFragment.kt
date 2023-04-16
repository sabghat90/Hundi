package com.sabghat.hundi.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.sabghat.hundi.R
import com.sabghat.hundi.data.Order
import com.sabghat.hundi.data.OrderViewModel
import com.sabghat.hundi.databinding.FragmentUpdateOrderBinding

class UpdateOrderFragment : Fragment() {

    // create binding object
    private var _binding: FragmentUpdateOrderBinding? = null
    private val binding get() = _binding!!

    // order object
    private lateinit var order: Order

    private val orderViewModel: OrderViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateOrderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateUI()

        binding.updateOrderLayout.btnAddOrder.text = getString(R.string.update_order)
        binding.updateOrderLayout.tvHeading.text = getString(R.string.update_order)

        binding.updateOrderLayout.btnAddOrder.setOnClickListener {
            updateOrder()
        }
    }

    private fun updateUI() {
        // get order object from bundle
        order = requireArguments().getParcelable("order")!!

        // set order object to binding object
        binding.updateOrderLayout.etReceiverName.setText(order.receiverName)
        binding.updateOrderLayout.etBalance.setText(order.transferAmount.toString())
        binding.updateOrderLayout.etCommission.setText(order.commission.toString())
    }

    private fun updateOrder() {
        // get updated order object
        val updatedOrder = Order(
            order.id,
            binding.updateOrderLayout.etReceiverName.text.toString(),
            binding.updateOrderLayout.etBalance.text.toString().toInt(),
            binding.updateOrderLayout.etCommission.text.toString().toInt(),
            order.transferDate
        )

        // update order
        orderViewModel.updateOrder(updatedOrder)
        findNavController().navigate(R.id.action_updateOrderFragment_to_orderListingFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}