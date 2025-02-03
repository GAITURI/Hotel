package activities

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hotel.MealCartViewModel
import com.example.hotel.PizzaDessertAdapter
import com.example.hotel.R
import com.example.hotel.databinding.FragmentMealCartBinding

class MealCart :AppCompatActivity() {



    private lateinit var pizzaDessertAdapter: PizzaDessertAdapter
    private lateinit var pizzaDessertRecyclerView:RecyclerView
    private lateinit var viewModel: MealCartViewModel
    private lateinit var errorTextView:TextView
    private lateinit var loadingProgressBar:ProgressBar

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setTheme(R.style.AppTheme)
   setContentView(R.layout.fragment_meal_cart)

    loadingProgressBar=findViewById(R.id.loadingProgressBar)
    errorTextView= findViewById(R.id.errorTextView)
    pizzaDessertRecyclerView= findViewById(R.id.pizzaDessertRecyclerView)
    pizzaDessertRecyclerView.layoutManager= LinearLayoutManager(this)
    pizzaDessertAdapter= PizzaDessertAdapter(emptyList())
    pizzaDessertRecyclerView.adapter= pizzaDessertAdapter
    viewModel= ViewModelProvider(this).get(MealCartViewModel::class.java)

    viewModel.pizzas.observe(this){ pizzas->viewModel.desserts.observe(this){ desserts->
        val combinedList= mutableListOf<Any>()
        combinedList.addAll(pizzas)
        combinedList.addAll(desserts)
        pizzaDessertAdapter=PizzaDessertAdapter(combinedList)
        pizzaDessertRecyclerView.adapter= pizzaDessertAdapter
    }
    }
viewModel.error.observe(this){
    error->errorTextView.text= error
    errorTextView.visibility= View.VISIBLE
}
viewModel.isLoading.observe(this){isLoading ->loadingProgressBar.visibility =if (isLoading) View.VISIBLE else View.GONE}

}

}