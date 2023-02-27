package com.reddy.presentation.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.reddy.data.sources.databases.entity.DriverEntity
import com.reddy.databinding.ItemDriverBinding

class DriverViewHolder(private val layoutBinding: ItemDriverBinding) :
    RecyclerView.ViewHolder(layoutBinding.root) {

    fun bind(driver: DriverEntity, onClickListener: (Int) -> Unit) {
        layoutBinding.tvDriverName.text = "${driver.firstName} ${driver.lastName}"
        layoutBinding.driverId.text = "${driver.id}"
        itemView.setOnClickListener {
            onClickListener.invoke(driver.id)
        }
    }
}

class DriverAdapter(private val onDriverClicked: (Int) -> Unit) :
    ListAdapter<DriverEntity, DriverViewHolder>(DriverDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DriverViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemDriverBinding.inflate(inflater, parent, false)
        return DriverViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DriverViewHolder, position: Int) {
        holder.bind(getItem(position), onDriverClicked)
    }
}

class DriverDiffCallback : DiffUtil.ItemCallback<DriverEntity>() {

    override fun areItemsTheSame(oldItem: DriverEntity, newItem: DriverEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DriverEntity, newItem: DriverEntity): Boolean {
        return oldItem == newItem
    }
}
