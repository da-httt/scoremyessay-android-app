package com.example.scoremyessay.ui.main.fragment.viewDetail.fragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.scoremyessay.data.model.orderAttribute.result.CriteriaResult
import com.example.scoremyessay.data.model.orderAttribute.result.ExtraResult
import com.example.scoremyessay.data.model.orders.OrderItem
import com.example.scoremyessay.databinding.ItemEssayCancelBinding
import com.example.scoremyessay.databinding.ItemResultCriteriaBinding
import com.example.scoremyessay.databinding.ItemResultExtraBinding
import com.example.scoremyessay.utils.extension.getNameByID

class ShowExtraResultAdapter : RecyclerView.Adapter<ShowExtraResultAdapter.ViewHolder>() {

    private var mListExtraResult = mutableListOf<ExtraResult>()

    fun updateData(liExtraResult: List<ExtraResult>) {
        mListExtraResult.clear()
        mListExtraResult.addAll(liExtraResult)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemResultExtraBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ExtraResult) {
            binding.txtTittle.text = item.option_name
            binding.txtComment.text = item.content
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemResultExtraBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mListExtraResult[position]

        holder.bind(item)
    }

    override fun getItemCount(): Int =
        mListExtraResult.size
}