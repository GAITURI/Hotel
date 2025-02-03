package com.example.hotel

import com.example.hotel.ui.theme.Burgers
import com.example.hotel.ui.theme.RetrofitInstance

class MealRepository {
    suspend fun getBurgers(): Array<Burgers> {
        return RetrofitInstance.api.getBurgers()
    }
    suspend fun getBurgers(burgerId:Int): Array<Burgers> {
        return RetrofitInstance.api.getBurgers(burgerId)

    }

}
//the suspend fun getPizzas() fetches a list of pizza objects from the API using the RetrofitInstance.api object
//it returns a list of pizza objecs