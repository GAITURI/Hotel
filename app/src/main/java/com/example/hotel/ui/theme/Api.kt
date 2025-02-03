package com.example.hotel.ui.theme

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
@Headers(
    "X-RapidAPI-Key:3a815503a7mshf6f3c58a65e3dd5p12e3cfjsnf5daa3f2e671",
    "X-RapidAPI-Host: pizza-and-desserts.p.rapidapi.com"
)
@GET("pizzas")
suspend fun getPizzas():PizzaListResponse

    @Headers(
        "X-RapidAPI-Key:3a815503a7mshf6f3c58a65e3dd5p12e3cfjsnf5daa3f2e671",
        "X-RapidAPI-Host: pizza-and-desserts.p.rapidapi.com"
    )
    @GET("pizzas/{id}")
    suspend fun getPizza(@Path("id")pizzaId:Int):Pizza
    @Headers(
        "X-RapidAPI-Key:3a815503a7mshf6f3c58a65e3dd5p12e3cfjsnf5daa3f2e671",
        "X-RapidAPI-Host: pizza-and-desserts.p.rapidapi.com"
    )
    @GET("desserts")
    suspend fun getDesserts():DessertListResponse
@Headers(
        "X-RapidAPI-Key:3a815503a7mshf6f3c58a65e3dd5p12e3cfjsnf5daa3f2e671",
        "X-RapidAPI-Host: pizza-and-desserts.p.rapidapi.com"
    )

@GET("desserts/{id}")
suspend fun getDessert(@Path("id")dessertId:Int):Dessert


}


