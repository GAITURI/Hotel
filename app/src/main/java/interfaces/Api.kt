package com.example.hotel.interfaces

import com.example.hotel.data.Burgers
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
@Headers(
    "X-RapidAPI-Key:3a815503a7mshf6f3c58a65e3dd5p12e3cfjsnf5daa3f2e671",
    "X-RapidAPI-Host:burgers-hub.p.rapidapi.com"
)
@GET("/burgers")
suspend fun getBurgers(
    @Query("id") id:String?= null,
    @Query("name") name:String?=null,
    @Query("imageUrls") imageUrls:String?=null,
    @Query("price") price:String?=null,
    @Query("description") description:String?=null,
) :List<Burgers>

    @Headers(
        "X-RapidAPI-Key:3a815503a7mshf6f3c58a65e3dd5p12e3cfjsnf5daa3f2e671",
        "X-RapidAPI-Host:burgers-hub.p.rapidapi.com"
    )
    @GET("burgers/{id}")
    suspend fun getBurgers(@Path("id")burgerId:Int):List<Burgers>


}


