package com.example.korkoapp.ui.element.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.korkoapp.adapter.CafeAdapter
import com.example.korkoapp.data.api.State
import com.example.korkoapp.databinding.FragmentCafeBinding
import com.example.korkoapp.ui.viewmodel.CafeViewModel

class CafeFragment : Fragment() {
    private val viewModel by viewModels<CafeViewModel>()
    private lateinit var binding: FragmentCafeBinding
    private lateinit var cafeAdapter: CafeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCafeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cafeAdapter = CafeAdapter()

        binding.recycleViewCafe.apply {
            layoutManager = GridLayoutManager(context, 2) // 2 columns
            adapter = cafeAdapter
        }

        viewModel.dataState.observe(viewLifecycleOwner){ dataState ->
            when(dataState.state){
                State.SUCCESS -> {
                    cafeAdapter.submitList(dataState.data)
                }
                State.ERROR -> {
                    Toast.makeText(context, "Error loading data. Please try again.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        viewModel.loadDataCafe()
    }

}