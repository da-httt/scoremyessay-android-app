package com.example.scoremyessay.ui.main.fragment.viewDetail.fragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.scoremyessay.data.model.orderAttribute.result.CriteriaResult
import com.example.scoremyessay.data.model.orders.OrderItem
import com.example.scoremyessay.databinding.ItemEssayCancelBinding
import com.example.scoremyessay.databinding.ItemResultCriteriaBinding
import com.example.scoremyessay.utils.extension.getNameByID

class ShowCriteriaAdapter : RecyclerView.Adapter<ShowCriteriaAdapter.ViewHolder>() {

    private var mListCriteriaComment = mutableListOf<CriteriaResult>()

    fun updateData(liCriteriaComment: List<CriteriaResult>) {
        mListCriteriaComment.clear()
        mListCriteriaComment.addAll(liCriteriaComment)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemResultCriteriaBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CriteriaResult) {
            binding.txtGrade.text = item.criteria_score
            binding.txtComment.text = item.criteria_comment
            binding.txtTittle.text = item.criteria_name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemResultCriteriaBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mListCriteriaComment[position]

        holder.bind(item)
    }

    override fun getItemCount(): Int =
        mListCriteriaComment.size
}