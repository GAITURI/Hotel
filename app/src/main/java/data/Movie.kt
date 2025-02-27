package com.example.hotel.data

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("Poster")
    var poster: String? = null,
    @SerializedName("id") val id: Int?,
    @SerializedName("title") val title: String?,
    @SerializedName("overview") val overview: String?,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("release_date") val releaseDate: String?
    )