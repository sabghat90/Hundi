package com.sabghat.hundi.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sabghat.hundi.R
import com.sabghat.hundi.data.OrderViewModel
import com.sabghat.hundi.databinding.FragmentOrderListingBinding


class OrderListingFragment : Fragment() {

    // create binding object
    private var _binding: FragmentOrderListingBinding? = null
    private val binding get() = _binding!!

    private val orderViewModel: OrderViewModel by viewModels()

    // create adapter object by passing the list of orders
    private val adapter by lazy {
        OrderListingAdapter(
            onClicked = { position, order ->
                // navigate to update order fragment and parcel the order object
                findNavController().navigate(
                    R.id.action_orderListingFragment_to_updateOrderFragment,
                    Bundle().apply {
                        putParcelable("order", order)
                    })
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentOrderListingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // set layout manager to recycler view
        binding.rvOrderList.layoutManager = LinearLayoutManager(requireContext())

        // observe the list of orders
        orderViewModel.getAllOrders.observe(viewLifecycleOwner) { orders ->
            // set the list of orders to adapter
            adapter.orders = orders
            // notify adapter that data has changed
            adapter.notifyDataSetChanged()
        }

        // set adapter to recycler view
        binding.rvOrderList.adapter = adapter

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_orderListingFragment_to_createOrderFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}