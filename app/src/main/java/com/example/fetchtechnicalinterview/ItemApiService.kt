package com.example.fetchtechnicalinterview

import retrofit2.Call
import retrofit2.http.GET

//basic get request in Retrofit
interface ItemApiService {
    @GET("hiring.json")
    fun getItems(): Call<List<Item>>
}