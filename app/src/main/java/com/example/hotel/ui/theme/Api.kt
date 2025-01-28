package com.example.hotel.ui.theme

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {


    @GET("Fetch_ProductList_By_PartnerId_SubCatId?")
    fun getData(@Query("SubCatId") Sid:String , @Query("PartnerId") Pid:String):Call<MealList>
}


