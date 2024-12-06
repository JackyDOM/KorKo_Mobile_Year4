package com.example.korkoapp.food_screen

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.korkoapp.R
import com.example.korkoapp.adapter.BannerAdapter
import com.example.korkoapp.adapter.DishAdapter
import com.example.korkoapp.api.State
import com.example.korkoapp.databinding.FragmentFoodBinding
import com.example.korkoapp.databinding.ViewHolderBannerBinding
import com.example.korkoapp.viewmodel.BannerViewModel
import com.example.korkoapp.viewmodel.DishViewModel

class FoodFragment : Fragment() {
    private val viewModel by viewModels<BannerViewModel>()
    private val viewModelDish by viewModels<DishViewModel>()
    private lateinit var binding: FragmentFoodBinding
    private lateinit var bannerAdapter: BannerAdapter
    private lateinit var dishAdapter: DishAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFoodBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bannerAdapter = BannerAdapter()
        dishAdapter = DishAdapter()

        binding.recycleViewBanner.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = bannerAdapter
        }

        binding.recycleViewDish.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = dishAdapter
        }

        viewModel.dataState.observe(viewLifecycleOwner){ dataState ->
            when (dataState.state) {
                State.SUCCESS -> {
                    Log.d("FoodFragment", "Data loaded successfully: ${dataState.data}")
                    bannerAdapter.submitList(dataState.data)
                }
                State.ERROR -> {
                    Log.e("FoodFragment", "Error loading data: ${dataState.errorMessage}")
                    Toast.makeText(context, "Error loading data. Please try again.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        viewModelDish.datstate.observe(viewLifecycleOwner){ dataState ->
            when(dataState.state){
                State.SUCCESS -> {
                    dishAdapter.submitList(dataState.data)
                }
                State.ERROR -> {
                    Toast.makeText(context, "Error loading data. Please try again.", Toast.LENGTH_SHORT).show()
                }
            }

        }

        viewModel.loadData()
        viewModelDish.loadDataDish()

    }
}