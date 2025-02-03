package com.example.hotel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotel.ui.theme.Dessert
import retrofit2.Callback
import com.example.hotel.ui.theme.Pizza
import com.example.hotel.ui.theme.RetrofitInstance
import kotlinx.coroutines.launch

import retrofit2.Call
import retrofit2.Response

class MealCartViewModel : ViewModel() {
    private val repository = MealRepository()
    private val _pizzas=MutableLiveData<List<Pizza>>()
    val pizzas:LiveData<List<Pizza>> = _pizzas
    private val _desserts=MutableLiveData<List<Dessert>>()
    val desserts:LiveData<List<Dessert>> = _desserts

    private val _error=MutableLiveData<String>()
    val error:LiveData<String> = _error

    private val _isLoading=MutableLiveData<Boolean>()

    val isLoading:LiveData<Boolean> = _isLoading

    init {
        loadData()
    }

    private fun loadData(){
        viewModelScope.launch {
            _isLoading.value=true
            try {
                val pizzaResponse=repository.getPizzas()
                _pizzas.value=pizzaResponse.pizzas
                val desertResponse= repository.getDesserts()
                _desserts.value=desertResponse.desserts
            }catch (e:Exception){
                _error.value="Error Loading data: ${e.message}"
            }finally {
                _isLoading.value=false
        }
    }
}
}