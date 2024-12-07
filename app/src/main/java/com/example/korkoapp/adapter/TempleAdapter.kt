package com.example.korkoapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.korkoapp.adapter.SeaAdapter.SeaViewHolder
import com.example.korkoapp.databinding.ViewHolderSeaBinding
import com.example.korkoapp.databinding.ViewHolderTempleBinding
import com.example.korkoapp.data.model.DataTemple
import com.squareup.picasso.Picasso

class TempleAdapter: ListAdapter<DataTemple, TempleAdapter.TempleViewHolder>(TempleDiffCallback()) {
    class TempleViewHolder(private var binding: ViewHolderTempleBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindDataTemple(dataTemple: DataTemple){
            Picasso.get().load(dataTemple.temple_image).into(binding.TempleImage)
            binding.txtTempleTittle.text = "ឈ្មោះ ${dataTemple.temple_name}"
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TempleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewHolderTempleBinding.inflate(inflater, parent, false)
        return TempleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TempleViewHolder, position: Int) {
        val Data = getItem(position)
        holder.bindDataTemple(Data)
    }

    class TempleDiffCallback: DiffUtil.ItemCallback<DataTemple>() {
        override fun areItemsTheSame(oldItem: DataTemple, newItem: DataTemple): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: DataTemple, newItem: DataTemple): Boolean {
            return oldItem.id == newItem.id
        }

    }
}