package com.sabghat.hundi.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.sabghat.hundi.R
import com.sabghat.hundi.data.Order
import com.sabghat.hundi.data.OrderViewModel
import com.sabghat.hundi.databinding.FragmentCreateOrderBinding


class CreateOrderFragment : Fragment() {

    private var _binding: FragmentCreateOrderBinding? = null
    private val binding get() = _binding!!

    private val orderViewModel: OrderViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCreateOrderBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.createOrderLayout.btnAddOrder.setOnClickListener {
            insertDataToDatabase()
        }
    }

    private fun insertDataToDatabase() {
        if (validation()) {
            val receiverName = binding.createOrderLayout.etReceiverName.text.toString()
            val balance = binding.createOrderLayout.etBalance.text.toString().toInt()
            val commission = binding.createOrderLayout.etCommission.text.toString().toInt()

            // get current date
            val date = java.util.Calendar.getInstance().time
            val dateFormat = java.text.SimpleDateFormat("yyyy-MM-dd")
            val currentDate = dateFormat.format(date)


            val order = Order(0, receiverName, balance, commission, currentDate)
            orderViewModel.insertOrder(order)

// clear edit text
            binding.createOrderLayout.etReceiverName.text?.clear()
            binding.createOrderLayout.etBalance.text?.clear()
            binding.createOrderLayout.etCommission.text?.clear()

            Toast.makeText(requireContext(), "Order added successfully", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_createOrderFragment_to_orderListingFragment)

        } else {
            Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
        }
    }

    private fun validation(): Boolean {
        val receiverName = binding.createOrderLayout.etReceiverName.text.toString()
        val balance = binding.createOrderLayout.etBalance.text.toString()
        val commission = binding.createOrderLayout.etCommission.text.toString()

        return !(receiverName.isEmpty() || balance.isEmpty() || commission.isEmpty())
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}