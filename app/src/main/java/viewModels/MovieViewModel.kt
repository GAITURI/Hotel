//package com.example.hotel.viewModels
//
//import android.util.Log
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import com.example.hotel.MovieRetrofitInstance
//import com.example.hotel.data.Movie
//import com.example.hotel.data.Search
//import com.example.hotel.interfaces.MovieApi
//import com.example.hotel.repositories.MovieRepository
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//class MovieViewModel(private val repository: MovieRepository) : ViewModel()  {
//
//    private val _movies = MutableLiveData<List<Movie>>()
//    val movies: LiveData<List<Movie>> = _movies
//    private val apiInterface:MovieApi= MovieRetrofitInstance.api
//
//    private val _error= MutableLiveData<String>()
//    val error: LiveData<String> = _error
//    private val _isLoading= MutableLiveData<Boolean>()
//    val isLoading: LiveData<Boolean> = _isLoading
//
//    fun searchMovies(query: String) {
//        _isLoading.value= true
//        Log.d("MovieViewModel", "Fetching movies with query: $query")
//        val apiKey="3a815503a7mshf6f3c58a65e3dd5p12e3cfjsnf5daa3f2e671"
//        val call=apiInterface.getSearchResultData(query, apiKey,1)
//        call.enqueue(object: Callback<Search> {
//            override fun onResponse(call: Call<Search>, response: Response<Search>) { _isLoading.value= false
//                Log.d("MovieViewModel","searchMovies onResponse: ${response.isSuccessful}")
//                if (response.isSuccessful){
//                    val searchResults= response.body()
//                    Log.d("MovieViewModel","searchMovies response body: $searchResults")
//                    if (searchResults !=null){
//                        if (searchResults.getResponse()== "True"){
//                            val movieList= searchResults.getSearch()?.map{searchItem-> Movie(
//                                id = searchItem.id.hashCode(),
//                                title = searchItem.title,
//                                overview = null,
//                                posterPath =searchItem.poster ,
//                                releaseDate = searchItem.releaseDate
//                            )
//
//
//                            } ?: emptyList()
//                            _movies.value= movieList
//                        }else{
//                            _error.value= "No results found"
//                        }
//                    }else{
//                        _error.value= "Empty response body"
//                    }
//                }else{
//                    _error.value= "Error: ${response.code()}"
//                }
//            }
//
//            override fun onFailure(call: Call<Search>, t: Throwable) {
//                _isLoading.value= false
//                Log.e("MovieViewModel","searchMovies onFailure: ${t.message}")
//                _error.value="Error: ${t.message}"
//            }
//        })
//
//
//
//
//
//    }
//
//
//
//}
