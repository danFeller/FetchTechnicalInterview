package com.example.fetchtechnicalinterview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : ComponentActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ItemAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) //change 2

        recyclerView = findViewById(R.id.rv_FetchList)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val itemList = listOf(Item("Item 1"), Item("Item 2"), Item("Item 3"))
        adapter = ItemAdapter(itemList)
        recyclerView.adapter = adapter

    }
}
