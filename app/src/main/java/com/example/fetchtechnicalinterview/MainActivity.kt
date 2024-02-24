package com.example.fetchtechnicalinterview

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


/*
This program makes the use of Retrofit for API Calls and RecyclerView for making dynamically loaded lists for faster load times and less
power usage when using the app

*/
class MainActivity : ComponentActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ItemAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //create views I plan to modify later
        recyclerView = findViewById(R.id.rv_FetchList)
        recyclerView.layoutManager = LinearLayoutManager(this)

        //creating Retrofit Client (see RetrofitClient.kt)
        RetrofitClient.instance.getItems().enqueue(object : retrofit2.Callback<List<Item>>{
            override fun onResponse(call: retrofit2.Call<List<Item>>, response: retrofit2.Response<List<Item>>){
                if (response.isSuccessful){
                    val result = response.body() // <--the information within response.body() is the list we are after.
                    //Log.d("message", result.toString())

                    // if we have a successful get request, it's stored in the list 'result'. I can then filter it like any Kotlin List.
                    if (result != null){
                        Log.d("Item List after if check", result.toString())
                        val antiNull = result.filter{
                            it.name != "" && it.name != null //assignment asks that I prune any item with no name or null
                        }

                        val groupings = antiNull.groupBy{it.listId}
                        for(v in groupings.values) {
                            Log.d("Should be several logs of each list", v.toString())
                        }

                        adapter = ItemAdapter(antiNull.sortedBy{it.listId}) //passes the final list into the recycler view to list out each element's name in the layout
                        recyclerView.adapter = adapter //set our Recycler View to our ItemAdapter object ()
                    }
                }
            }

            //handles failure for any reason
            override fun onFailure(call: retrofit2.Call<List<Item>>, t: Throwable){
                // Handle failure
                println(t.message)
            }
        })




    }
}
