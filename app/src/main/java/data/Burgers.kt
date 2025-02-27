package com.example.hotel.data

import com.google.gson.annotations.SerializedName


data class Burgers(
    val id:Int,
    val name:String,
    @SerializedName("images")
    val imageUrls:List<ImageData>?= null,
    val description:String
){
    fun getImageUrl(): String? {
        return when {
            imageUrls.isNullOrEmpty() -> null
            !imageUrls[0].largeImageUrl.isNullOrEmpty() -> imageUrls[0].largeImageUrl
            !imageUrls[0].mediumImageUrl.isNullOrEmpty() -> imageUrls[0].mediumImageUrl
            !imageUrls[0].smallImageUrl.isNullOrEmpty() -> imageUrls[0].smallImageUrl
            else -> null
        }
    }
}

