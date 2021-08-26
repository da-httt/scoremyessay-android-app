package com.example.scoremyessay.ui.main.fragment.createOrder.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.scoremyessay.data.model.ui.ThanhToanOption
import com.example.scoremyessay.databinding.ItemOptionInformationBinding
import com.example.scoremyessay.utils.extension.convertPriceToUI

class RecyclerThanhToanAdapter : RecyclerView.Adapter<RecyclerThanhToanAdapter.ViewHolder>() {

    private var mLiThanhToanOptions = mutableListOf<ThanhToanOption>()

    fun updateData(liThanhToanOptions: MutableList<ThanhToanOption>) {
        mLiThanhToanOptions.clear()
        mLiThanhToanOptions.addAll(liThanhToanOptions)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemOptionInformationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun binding(item: ThanhToanOption) {
            binding.txtName.text = item.name
            binding.txtPrice.text = item.price.toString().convertPriceToUI()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemOptionInformationBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mLiThanhToanOptions[position]

        holder.binding(item)
    }

    override fun getItemCount(): Int = mLiThanhToanOptions.size
}