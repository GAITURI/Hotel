//package com.example.hotel.interfaces
//
//import com.example.hotel.data.Movie
//import com.example.hotel.data.Search
//import retrofit2.Call
//import retrofit2.http.GET
//import retrofit2.http.Headers
//import retrofit2.http.Path
//import retrofit2.http.Query
//
//interface MovieApi {
//    @Headers(
//        "X-RapidAPI-Key:3a815503a7mshf6f3c58a65e3dd5p12e3cfjsnf5daa3f2e671",
//        "X-RapidAPI-Host:advanced-movie-search.p.rapidapi.com"
//    )
//    @GET("/")
//    fun getSearchResultData(
//        @Query("s") searchTitle:String?= null,
//        @Query("apiKey") apiKey:String?= null,
//        @Query("page") page:Int?= null
//    ):Call<Search>
//@GET("?type=movie")
//      fun searchMovies(
//        @Query("query") query:String?= null,
//        @Query("title") title:String?= null,
//        @Query("year") year:Int?= null,
//        @Query("page") page:Int?= null,
//        @Query("genre") genre:String?= null,
//        @Query("actor") actor:String?= null,
//        @Query("director") director:String?= null):Call<Search>
//     @GET("movies/{id}")
//  fun getMovie(@Path("id")movieId:Int):Call<Movie>
//
//}