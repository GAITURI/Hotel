package com.example.hotel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hotel.ui.theme.Data
import retrofit2.Callback
import com.example.hotel.ui.theme.MealList
import com.example.hotel.ui.theme.RetrofitInstance

import retrofit2.Call
import retrofit2.Response

class MealCartViewModel : ViewModel() {
//it is deisgned to fetch and hold a list of meals from an API using Retrofit.
    //it then makes this data observable to the UI
    private var MealLiveData = MutableLiveData<List<Data>>()

    //this function is responsible for fetching meal data from the API
    //and updating the MealLiveData with the fetched data
    //the enqueue() method is used to make the API call asynchronously
    fun getMeals(){
        RetrofitInstance.api.getData("507","180").enqueue(object : Callback<MealList> {
        override  fun onResponse(call: Call<MealList>, response: Response<MealList>){
          if (response.body() != null) {
              MealLiveData.value = response.body()!!. Data
          }else{
              return

            }

        }
            override fun onFailure(call: Call<MealList>, t:Throwable){
                Log.d("TAG", t.message.toString())
            }
        })



    }
    //this function returns the meallivedata as a livedata object.
    //this allows the UI to observe changes to the meal data
    fun observeMealLiveData(): LiveData<List<Data>>{
        return MealLiveData
    }

}//the UI calls getMeals() to initiate the API request
//getMeals() makes the API call asynschronously using retrofit
//when the API response is recieved, onResponse() or onFailure() is called
//if the response is succesful, MealLive Data is updated with the meal data
