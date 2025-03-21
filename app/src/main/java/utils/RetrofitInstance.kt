package utils

import interfaces.Api
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://burgers-hub.p.rapidapi.com/burgers/"
   private val loggingInterceptor=HttpLoggingInterceptor().apply {
       level= HttpLoggingInterceptor.Level.BODY

   }
    private val client= OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor{chain->
            val request= chain.request().newBuilder()
                .addHeader("X-RapidAPI-Key", "3a815503a7mshf6f3c58a65e3dd5p12e3cfjsnf5daa3f2e671")
                .addHeader("X-RapidAPI-Host", "burgers-hub.p.rapidapi.com")
                .build()
            chain.proceed(request)

        }
        .build()

    private val retrofit:Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api: Api by lazy {
        retrofit.create(Api::class.java)
    }
    }


//the primary purpose of the RetrofitInstance object is to create and manage a single instance of the retrofit client
//the object RetrofitInstance declares a singleton object named Retrofit instance. Singleton objects are created lazily, meaning they are only created when they are first accessed

//the val api: Api by lazy{}
//this declares a property named api of type Api(API interface)
//the lazy delegate optimizes performance by avoiding unnecessary setup during app startup
//the retrofit.builder() is responsible for building the retrofit instance and creating the API Service
//.addConverterFactory, adds the Gson converter factory to the retrofit instance