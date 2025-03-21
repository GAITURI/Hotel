//package com.example.hotel.repositories
//
//import android.util.Log
//import com.example.hotel.data.Search
//import com.example.hotel.interfaces.MovieApi
//import okio.IOException
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.HttpException
//import retrofit2.Response
//
//class MovieRepository(private val api:MovieApi) {
//     fun searchMovies(
//        query:String?= null,
//        title:String?=null,
//        year: Int? = null,
//        page: Int? = null,
//        genre: String? = null,
//        actor: String? = null,
//        director: String? = null,
//        callback:(Search?, Exception?)-> Unit
//    ){
//         Log.d("MovieRepository", "Fetching movies with query: $query, title: $title, year: $year, page: $page, genre: $genre, actor: $actor, director: $director")
//        api.searchMovies(query, title, year, page, genre, actor, director).enqueue(object :Callback<Search> {
//            override fun onResponse(call: Call<Search>,response: Response<Search>){
//                Log.d("MovieRepository", "Response received: ${response.body()}")
//                if(response.isSuccessful){
//                        Log.d("MovieRepository", "Response body: ${response.body()}")
//                    callback(response.body(), null)
//                }else{
//                    callback(null, HttpException(response))
//                }
//            }
//
//            override fun onFailure(call: Call<Search>, t: Throwable) {
//                Log.e("MovieRepository", "Error fetching movies: ${t.message}")
//                callback(null, IOException(t.message))
//            }
//        })
//    }
//
//
//
//
//    suspend fun getSearchResultData(searchTitle:String, apiKey: String,pageIndex:Int ):Search{
//        try{
//            val response= api.getSearchResultData(searchTitle,apiKey,pageIndex).execute()
//            if (response.isSuccessful){
//                return response.body()!!
//            }else{
//                throw HttpException(response)
//            }
//        }catch (e:Exception){
//            throw IOException("Error fetching search results", e)
//        }
//    }
//}
////the suspend fun getSearch ResultData
////the parameters for the search, title, apikey and pageindex
////the movieviewmodel will call the getSearchResultData function of the movie repository
////the movieviewmodel will use the injected movieapi to make the network request to the movie api
////the API will return a response which will be parsed into a searchresults object
////the movierepository will return the searchresults  object to the movieviewmodel
////the movieviewmodel will then update the UI with the search results