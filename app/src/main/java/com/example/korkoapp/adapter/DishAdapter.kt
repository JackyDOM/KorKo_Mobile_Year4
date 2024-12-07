package com.example.korkoapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.korkoapp.databinding.ViewHolerDishBinding
import com.example.korkoapp.data.model.Data
import com.squareup.picasso.Picasso

class DishAdapter:  ListAdapter<Data, DishAdapter.DishViewHolder>(DishDiffCallback()){
    class DishViewHolder(private var binding: ViewHolerDishBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindDataDish(data: Data){
            Picasso.get().load(data.food_image).into(binding.DishImage)
            binding.txtDishTittle.text = "ឈ្មោះ ${data.food_name}"
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewHolerDishBinding.inflate(inflater, parent, false)
        return DishViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
        val Data = getItem(position)
        holder.bindDataDish(Data)
    }

    class DishDiffCallback: DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.id == newItem.id
        }

    }
}