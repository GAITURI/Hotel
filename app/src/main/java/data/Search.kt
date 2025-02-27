package com.example.hotel.data

import com.google.gson.annotations.SerializedName

data class Search (
    @SerializedName("Response")
   private var response: String?= null,
    @SerializedName("Search")
    private var search:List<Movie>?=null,
    @SerializedName("results") val searchResults: List<Movie>?,
    @SerializedName("total_results") val totalResults: Int?,
    @SerializedName("total_pages") val totalPages: Int?
    // ... other fields ...



){

    fun getResponse():String?{
        return response
    }
    fun getSearch():List<Movie>?{
        return search
    }
}


