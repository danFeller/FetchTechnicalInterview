package com.example.fetchtechnicalinterview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

//RecyclerView is made from an abstract class with functions that need to be overridden as seen below
class ItemAdapter(private val itemList: List<Item>): RecyclerView.Adapter<ItemAdapter.MyViewHolder>(){
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var textView: TextView = itemView.findViewById(R.id.itemTextView) // itemTextView holds the layout for an individual item
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return MyViewHolder(itemView)
    }

    //Probably the most important of the functions. This formats the Item Object and turns it into a text view to be put in the recycler view
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = itemList[position]
        val info = currentItem.name
        holder.textView.text = info
    }


    override fun getItemCount() = itemList.size
}