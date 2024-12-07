package com.example.korkoapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.korkoapp.adapter.DishAdapter.DishViewHolder
import com.example.korkoapp.databinding.ViewHolderSeaBinding
import com.example.korkoapp.databinding.ViewHolerDishBinding
import com.example.korkoapp.data.model.DataSea
import com.squareup.picasso.Picasso

class SeaAdapter: ListAdapter<DataSea, SeaAdapter.SeaViewHolder>(SeaDiffUtil()) {
    class SeaViewHolder(private var binding: ViewHolderSeaBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindDataSea(dataSea: DataSea){
            Picasso.get().load(dataSea.sea_image).into(binding.SeaImage)
            binding.txtSeaTittle.text = "ឈ្មោះ ${dataSea.sea_name}"
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewHolderSeaBinding.inflate(inflater, parent, false)
        return SeaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SeaViewHolder, position: Int) {
        val Data = getItem(position)
        holder.bindDataSea(Data)
    }

    class SeaDiffUtil: DiffUtil.ItemCallback<DataSea>() {
        override fun areItemsTheSame(oldItem: DataSea, newItem: DataSea): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: DataSea, newItem: DataSea): Boolean {
            return oldItem.id == newItem.id
        }

    }
}