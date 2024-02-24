package com.example.fetchtechnicalinterview

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//setup for calling to our API with Retrofit
object RetrofitClient {
    private const val BASE_URL = "https://fetch-hiring.s3.amazonaws.com/"
    val instance: ItemApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ItemApiService::class.java)
    }
}