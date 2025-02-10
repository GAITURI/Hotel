package com.example.hotel

import android.util.Log
import com.example.hotel.ui.theme.Api
import com.example.hotel.ui.theme.Burgers
import com.example.hotel.ui.theme.RetrofitInstance
import retrofit2.HttpException
import java.io.IOException

class MealRepository (private val api:Api){
    suspend fun getBurgers(): List<Burgers> {
        val results = RetrofitInstance.api.getBurgers()
        Log.d("MealRepository", "Fetching burgers with ID: $results")
        try {
            return RetrofitInstance.api.getBurgers()
        }catch (e:HttpException){
            throw HttpException(e.response()!!)
        }catch (e: IOException){
            throw IOException(e.message)
        }catch (e:Exception){
            throw Exception(e.message)
        }
    }
    suspend fun getBurgers(burgerId:Int): List<Burgers> {
        return RetrofitInstance.api.getBurgers(burgerId)

    }

}
//the suspend fun getPizzas() fetches a list of burger objects from the API using the RetrofitInstance.api object
//it returns a list of pizza objecs