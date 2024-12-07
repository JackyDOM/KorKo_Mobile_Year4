package com.example.korkoapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.korkoapp.adapter.DishAdapter.DishViewHolder
import com.example.korkoapp.databinding.ViewHolderSoupBinding
import com.example.korkoapp.databinding.ViewHolerDishBinding
import com.example.korkoapp.model.Data
import com.squareup.picasso.Picasso

class SoupAdapter: ListAdapter<Data, SoupAdapter.SoupViewHolder>(SoupDiffCallback()) {
    class SoupViewHolder(private var binding: ViewHolderSoupBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindDataSoup(data: Data){
            Picasso.get().load(data.food_image).into(binding.SoupImage)
            binding.txtSoupTittle.text = "ឈ្មោះ ${data.food_name}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoupViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewHolderSoupBinding.inflate(inflater, parent, false)
        return SoupViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SoupViewHolder, position: Int) {
        val Data = getItem(position)
        holder.bindDataSoup(Data)
    }

    class SoupDiffCallback: DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.id == newItem.id
        }

    }
}