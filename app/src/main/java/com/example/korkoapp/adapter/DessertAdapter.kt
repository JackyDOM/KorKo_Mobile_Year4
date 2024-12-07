package com.example.korkoapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.korkoapp.adapter.DishAdapter.DishViewHolder
import com.example.korkoapp.databinding.ViewHolderDessertBinding
import com.example.korkoapp.databinding.ViewHolerDishBinding
import com.example.korkoapp.model.Data
import com.squareup.picasso.Picasso

class DessertAdapter: ListAdapter<Data, DessertAdapter.DessertViewHolder>(DessertDiffCallback()) {
    class DessertViewHolder(private var binding: ViewHolderDessertBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindDataDessert(data: Data){
            Picasso.get().load(data.food_image).into(binding.DessertImage)
            binding.txtDessertTittle.text = "ឈ្មោះ ${data.food_name}"
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DessertViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewHolderDessertBinding.inflate(inflater, parent, false)
        return DessertViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DessertViewHolder, position: Int) {
        val Data = getItem(position)
        holder.bindDataDessert(Data)
    }

    class DessertDiffCallback: DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.id == newItem.id
        }

    }
}