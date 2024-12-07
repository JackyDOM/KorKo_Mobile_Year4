package com.example.korkoapp.ui.element.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.korkoapp.adapter.TempleAdapter
import com.example.korkoapp.data.api.State
import com.example.korkoapp.databinding.FragmentTempleBinding
import com.example.korkoapp.ui.viewmodel.TempleViewModel

class TempleFragment : Fragment() {
    private val viewModel by viewModels<TempleViewModel>()
    private lateinit var binding: FragmentTempleBinding
    private lateinit var templeAdapter: TempleAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTempleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        templeAdapter = TempleAdapter()

        binding.recycleViewTemple.apply {
            layoutManager = GridLayoutManager(context, 2) // 2 columns
            adapter = templeAdapter
        }

        viewModel.dataState.observe(viewLifecycleOwner){ dataState ->
            when(dataState.state){
                State.SUCCESS -> {
                    templeAdapter.submitList(dataState.data)
                }
                State.ERROR -> {
                    Toast.makeText(context, "Error loading data. Please try again.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        viewModel.loadDataTemple()
    }
}