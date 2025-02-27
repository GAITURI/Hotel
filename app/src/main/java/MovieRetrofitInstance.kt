package com.example.hotel

import com.example.hotel.interfaces.MovieApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieRetrofitInstance {


    private const val BASE_URL="https://advanced-movie-search.p.rapidapi.com/search/"

private  val loggingInterceptor=HttpLoggingInterceptor().apply {
    level= HttpLoggingInterceptor.Level.BODY
}

private val client= OkHttpClient.Builder()
    .addInterceptor(loggingInterceptor)
    .build()

private val retrofit:Retrofit by lazy{
    Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

val api: MovieApi by lazy{
    retrofit.create(MovieApi::class.java)
}




}
//the primary purpose of the RetrofitInstance object is to create and manage a single instance of the retrofit client
//the object RetrofitInstance declares a singleton object named Retrofit instance. Singleton objects are created lazily, meaning they are only created when they are first accessed