package com.example.scoremyessay.ui.main.fragment.viewDetail.fragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.scoremyessay.data.model.orderAttribute.result.ai.SpellingError
import com.example.scoremyessay.databinding.ItemViewEssayAiDetailBinding

class SpellErrorRecyclerViewAdapter :
    RecyclerView.Adapter<SpellErrorRecyclerViewAdapter.ViewHolder>() {

    private var mLiSpellError = mutableListOf<SpellingError>()

    fun updateData(liSpellError: List<SpellingError>) {
        mLiSpellError.clear()
        mLiSpellError.addAll(liSpellError)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemViewEssayAiDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mLiSpellError[position]

        holder.bind(item)
    }

    override fun getItemCount(): Int =
        mLiSpellError.size

    inner class ViewHolder(private val binding: ItemViewEssayAiDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SpellingError) {
            binding.txtErrorSentence.text = item.sentence
            binding.txtGoiYSua.text = item.suggested_word
            binding.txtTuSai.text = item.word
        }
    }
}