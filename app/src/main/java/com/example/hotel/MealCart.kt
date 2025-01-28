package com.example.hotel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hotel.databinding.FragmentMealCartBinding

class MealCart :AppCompatActivity() {


    private lateinit var binding: FragmentMealCartBinding
    private lateinit var ApiAdapter: ApiAdapter
    private lateinit var viewModel: MealCartViewModel


    private fun prepareRecyclerView() {
      ApiAdapter= ApiAdapter()
        binding.rcv.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = ApiAdapter
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= FragmentMealCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prepareRecyclerView()
        viewModel= ViewModelProvider(this)[MealCartViewModel::class.java]
        viewModel.getMeals()
        viewModel.observeMealLiveData().observe(this) { mealList ->
            ApiAdapter.setMealList(mealList)
        }
    }
}