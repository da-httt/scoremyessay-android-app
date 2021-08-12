package com.example.scoremyessay.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.scoremyessay.data.model.orders.OrderItem
import com.example.scoremyessay.R

class EssayAdapter(private val essayList: List<OrderItem>): RecyclerView.Adapter<EssayAdapter.EssayViewHolder>() {
    inner class EssayViewHolder(v: View): RecyclerView.ViewHolder(v){
        var essayGrade = v.findViewById<TextView>(R.id.txtGrade)
        var essayType = v.findViewById<TextView>(R.id.txtType)
        var essayTitle = v.findViewById<TextView>(R.id.txtTitle)
        var essayTeacherName = v.findViewById<TextView>(R.id.txtNameTeacher)
        var essayTeacherAvatar = v.findViewById<TextView>(R.id.imgAvatar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EssayViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        var v = inflater.inflate(R.layout.item_essay_done,parent,false)
        return EssayViewHolder(v)
    }

    override fun onBindViewHolder(holder: EssayViewHolder, position: Int) {
        val essayItem = essayList[position]
//        holder.e
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }


}