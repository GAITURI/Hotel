package com.example.hotel.repositories

import android.util.Log
import com.example.hotel.interfaces.Api
import com.example.hotel.data.Burgers
import com.example.hotel.ui.theme.RetrofitInstance
import retrofit2.HttpException
import java.io.IOException

class MealRepository (private val api: Api){
    suspend fun getBurgers(
    id: String?,
    name: String?,
    description: String?,
    ): List<Burgers> {
        Log.d(
            "MealRepository",
            "Fetching burgers with parameters: id=$id, name=$name, description=$description"
        )
        try {
            val burgers = RetrofitInstance.api.getBurgers(id, name, description)
            burgers.forEach {
                Log.d("MealRepository", "Fetched burger: ${it.name}, imageUrls: ${it.getImageUrl()}")
            }
            return burgers
        } catch (e: HttpException) {
            Log.e("MealRepository", "HTTP error: ${e.message}")
            throw HttpException(e.response()!!)
        } catch (e: IOException) {
            Log.e("MealRepository", "IO error: ${e.message}")
            throw IOException(e.message)
        } catch (e: Exception) {
            Log.e("MealRepository", "General error: ${e.message}")
            throw Exception(e.message)
        }
    }

    suspend fun getBurgers(burgerId: Int): List<Burgers> {
        try {
            val burgers = RetrofitInstance.api.getBurgers(burgerId)
            burgers.forEach {
                Log.d("MealRepository", "Fetched burger: ${it.name}, imageUrl: ${it.getImageUrl()}")
            }
            return burgers
        } catch (e: HttpException) {
            Log.e("MealRepository", "HTTP error: ${e.message}")
            throw HttpException(e.response()!!)
        } catch (e: IOException) {
            Log.e("MealRepository", "IO error: ${e.message}")
            throw IOException(e.message)
        } catch (e: Exception) {
            Log.e("MealRepository", "General error: ${e.message}")
            throw Exception(e.message)
        }
    }

}
//the suspend fun getPizzas() fetches a list of burger objects from the API using the RetrofitInstance.api object
//it returns a list of pizza objecs