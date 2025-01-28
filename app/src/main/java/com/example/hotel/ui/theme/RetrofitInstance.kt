package com.example.hotel.ui.theme

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val api:Api by lazy {
        Retrofit.Builder()
            .baseUrl("https://www.deshizon.com/api/")
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