package com.example.korkoapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.korkoapp.databinding.ViewHolderBannerBinding
import com.example.korkoapp.data.model.DataBanner
import com.squareup.picasso.Picasso

class BannerAdapter: ListAdapter<DataBanner, BannerAdapter.BannerViewHolder>(BannerDiffCallback()) {
    class BannerViewHolder(private var binding: ViewHolderBannerBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindDataBanner(dataBanner: DataBanner){
            Picasso.get().load(dataBanner.image_banner).into(binding.bannerImage)
            binding.txtTitle.text = "${dataBanner.name}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewHolderBannerBinding.inflate(inflater, parent, false)
        return BannerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        val Data = getItem(position)
        holder.bindDataBanner(Data)
    }

    class BannerDiffCallback: DiffUtil.ItemCallback<DataBanner>() {
        override fun areItemsTheSame(oldItem: DataBanner, newItem: DataBanner): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: DataBanner, newItem: DataBanner): Boolean {
            return oldItem.id == newItem.id
        }

    }
}


