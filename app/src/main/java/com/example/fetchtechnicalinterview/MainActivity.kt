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
                        val antiNull = result.filter{
                            it.name != "" && it.name != null //assignment asks that I prune any item with no name or null
                        }

                        //creates a map with 4 lists, then sorts all the elements in our result into each list based on their listId
                        val groupings = antiNull.groupBy{it.listId}

                        /*
                        The block below concatenates the 4 lists created by grouping the elements of the api call. The 4 lists
                        became elements of a map organized by their listId. I then sorted each by listId, then name and combined them
                        back together with "separation" elements that divided each of the 4 lists while remaining in one recycler view.
                         */
                        val finalList = listOf(Item("------ ListId: 1 -----", 0, 0)) +
                                        groupings.getValue(1).sortedWith(compareBy({it.listId}, {it.name})) +
                                        listOf(Item("", 0, 0), Item("------ ListId: 2 -----", 0, 0)) +
                                        groupings.getValue(2).sortedWith(compareBy({it.listId}, {it.name})) +
                                        listOf(Item("", 0, 0), Item("------ ListId: 3 -----", 0, 0)) +
                                        groupings.getValue(3).sortedWith(compareBy({it.listId}, {it.name})) +
                                        listOf(Item("", 0, 0), Item("------ ListId: 4 -----", 0, 0)) +
                                        groupings.getValue(4).sortedWith(compareBy({it.listId}, {it.name}))

                        adapter = ItemAdapter(finalList) //passes the final list into the recycler view to list out each element's name in the layout
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