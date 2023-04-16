package com.sabghat.hundi.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.sabghat.hundi.R
import com.sabghat.hundi.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    // make binding
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnOrdersList.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_orderListingFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}