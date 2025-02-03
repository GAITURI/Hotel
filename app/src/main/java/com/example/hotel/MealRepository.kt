package com.example.hotel

import com.example.hotel.ui.theme.Dessert
import com.example.hotel.ui.theme.DessertListResponse
import com.example.hotel.ui.theme.Pizza
import com.example.hotel.ui.theme.PizzaListResponse
import com.example.hotel.ui.theme.RetrofitInstance

class MealRepository {
    suspend fun getPizzas():PizzaListResponse{
        return RetrofitInstance.api.getPizzas()
    }
    suspend fun getPizza(pizzaId:Int): Pizza {
        return RetrofitInstance.api.getPizza(pizzaId)

    }

    suspend fun getDesserts():DessertListResponse{
        return RetrofitInstance.api.getDesserts()
    }
    suspend fun getDessert(dessertId:Int): Dessert {
        return RetrofitInstance.api.getDessert(dessertId)
    }
}