package com.example.hotel.data

import com.google.gson.annotations.SerializedName

data class ImageData (

    @SerializedName("sm") val smallImageUrl:String?,
    @SerializedName("md") val mediumImageUrl:String?,
    @SerializedName("lg") val largeImageUrl:String?

)