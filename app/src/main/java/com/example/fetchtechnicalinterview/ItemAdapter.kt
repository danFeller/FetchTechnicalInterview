package com.example.fetchtechnicalinterview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.compose.runtime.currentRecomposeScope
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(private val itemList: List<Item>): RecyclerView.Adapter<ItemAdapter.MyViewHolder>(){
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var textView: TextView = itemView.findViewById(R.id.itemTextView) // itemTextView holds the layout for an individual item
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.textView.text = currentItem.name
    }


    override fun getItemCount() = itemList.size
}