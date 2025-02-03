package com.example.hotel.ui.theme

data class PizzaListResponse (val pizzas:List<Pizza>)
data class Pizza(
    val id:Int,
    val name:String,
    val imageUrls:String,
    val description:String
)
data class DessertListResponse (val desserts:List<Dessert>)
data class Dessert(
    val id:Int,
    val name:String,
    val imageUrls:String,
    val description: String
)