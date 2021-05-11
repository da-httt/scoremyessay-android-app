package com.example.scoreMyEssay.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.scoreMyEssay.data.model.IntroSliderModel
import com.example.scoreMyEssay.R
import com.example.scoreMyEssay.databinding.SlideItemContainerBinding

class IntroSliderAdapter (private val introSlides: List<IntroSliderModel>): RecyclerView.Adapter<IntroSliderAdapter.IntroSlideViewHolder>(){
    inner class IntroSlideViewHolder(view: View) : RecyclerView.ViewHolder(view)
    {
        private val _binding: SlideItemContainerBinding? = null
        private val binding get() = _binding!!


        private val textTitle = view.findViewById<TextView>(R.id.textTittle)
        private val textDescription = view.findViewById<TextView>(R.id.textDescription)
        private val imageIcon = view.findViewById<ImageView>(R.id.imageSlideIcon)

        fun bind(introSlide: IntroSliderModel)
        {
            textTitle.text = introSlide.tille
            textDescription.text = introSlide.description
            imageIcon.setImageResource(introSlide.icon)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntroSlideViewHolder {
        return IntroSlideViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.slide_item_container,parent,false)
        )
    }

    override fun onBindViewHolder(holder: IntroSlideViewHolder, position: Int) {
        holder.bind(introSlides[position])
    }

    override fun getItemCount(): Int {
        return introSlides.size
    }
}