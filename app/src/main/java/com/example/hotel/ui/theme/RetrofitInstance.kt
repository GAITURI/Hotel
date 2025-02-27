package com.example.hotel.ui.theme

import com.example.hotel.interfaces.Api
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
private val loggingInterceptor= HttpLoggingInterceptor().apply{
    level=HttpLoggingInterceptor.Level.BODY
}
    private val client= OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()
    val api: Api by lazy {
        Retrofit.Builder()
            .baseUrl("https://burgers-hub.p.rapidapi.com/burgers/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }
}
//the primary purpose of the RetrofitInstance object is to create and manage a single instance of the retrofit client
//the object RetrofitInstance declares a singleton object named Retrofit instance. Singleton objects are created lazily, meaning they are only created when they are first accessed

//the val api: Api by lazy{}
//this declares a property named api of type Api(API interface)
//the lazy delegate optimizes performance by avoiding unnecessary setup during app startup
//the retrofit.builder() is responsible for building the retrofit instance and creating the API Service
//.addConverterFactory, adds the Gson converter factory to the retrofit instance