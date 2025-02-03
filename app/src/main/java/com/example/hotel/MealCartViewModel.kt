package com.example.hotel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotel.ui.theme.Burgers
import kotlinx.coroutines.launch

class MealCartViewModel : ViewModel() {
    //an instance of meal repository assigned to the repository property
    private val repository = MealRepository()
    //create a new MutableLiveData that can hold a list of Pizza Objects
    private val _burgers=MutableLiveData<Array<Burgers>>()
    //pizzas isa LiveData that holdsa list of Pizza objects
    //this is how the UI can observe changes to the list of pizzas
    //the viewmodel exposes the list of pizzas to the UI through the pizzas property
    val burgers:LiveData<Array<Burgers>> = _burgers


    private val _error=MutableLiveData<String>()
    val error:LiveData<String> = _error

    private val _isLoading=MutableLiveData<Boolean>()

    val isLoading:LiveData<Boolean> = _isLoading

    init {
        loadData()
    }
//Responsible for fetching the pizza and dessert data from the repository and updating the LiveData objects
    private fun loadData(){
        viewModelScope.launch {
            _isLoading.value=true
            try {
                val burgers=repository.getBurgers()
                _burgers.value=burgers

            }catch (e:Exception){
                _error.value="Error Loading data: ${e.message}"
            }finally {
                _isLoading.value=false
        }
    }
}
}