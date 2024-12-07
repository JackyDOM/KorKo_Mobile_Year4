package com.example.korkoapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.korkoapp.adapter.TempleAdapter.TempleViewHolder
import com.example.korkoapp.databinding.ViewHolderCafeBinding
import com.example.korkoapp.databinding.ViewHolderTempleBinding
import com.example.korkoapp.data.model.DataCafe
import com.squareup.picasso.Picasso

class CafeAdapter: ListAdapter<DataCafe, CafeAdapter.CafeViewHolder>(CafeDiffCallback()) {
    class CafeViewHolder(private var binding: ViewHolderCafeBinding): RecyclerView.ViewHolder(binding.root) {
        fun loadDataCafe(dataCafe: DataCafe){
            Picasso.get().load(dataCafe.cafe_image).into(binding.CafeImage)
            binding.txtCafeTittle.text = "ឈ្មោះ ${dataCafe.cafe_name}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CafeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewHolderCafeBinding.inflate(inflater, parent, false)
        return CafeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CafeViewHolder, position: Int) {
        val Data = getItem(position)
        holder.loadDataCafe(Data)
    }

    class CafeDiffCallback: DiffUtil.ItemCallback<DataCafe>() {
        override fun areItemsTheSame(oldItem: DataCafe, newItem: DataCafe): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: DataCafe, newItem: DataCafe): Boolean {
            return oldItem.id == newItem.id
        }

    }
}