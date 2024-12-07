package com.example.korkoapp.sea_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.korkoapp.R
import com.example.korkoapp.adapter.SeaAdapter
import com.example.korkoapp.api.State
import com.example.korkoapp.databinding.FragmentSeaBinding
import com.example.korkoapp.viewmodel.SeaViewModel

class SeaFragment : Fragment() {
    private val viewModel by viewModels<SeaViewModel>()
    private lateinit var binding: FragmentSeaBinding
    private lateinit var seaAdapter: SeaAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSeaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        seaAdapter = SeaAdapter()

        binding.recycleViewSea.apply {
            layoutManager = GridLayoutManager(context, 2) // 2 columns
            adapter = seaAdapter
        }

        viewModel.dataState.observe(viewLifecycleOwner){ dataState ->
            when(dataState.state){
                State.SUCCESS -> {
                    seaAdapter.submitList(dataState.data)
                }
                State.ERROR -> {
                    Toast.makeText(context, "Error loading data. Please try again.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        viewModel.loadDataSea()
    }
}