package activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hotel.PizzaDessertAdapter
import com.example.hotel.R
import data.Burgers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import utils.RetrofitInstance

class MealCart :AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var manager: RecyclerView.LayoutManager
    private lateinit var adapter: RecyclerView.Adapter<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mealcart)
        manager = LinearLayoutManager(this)
        getAllData()

    }

    private fun getAllData() {
        val apiService=RetrofitInstance.api
        val call=apiService.getAllBurgers().enqueue(object: Callback<List<Burgers>> {
            override fun onResponse(call: Call<List<Burgers>>, response: Response<List<Burgers>>) {
               if(response.isSuccessful){
                   recyclerView= findViewById<RecyclerView>(R.id.pizzaDessertRecyclerView).apply {
                       adapter=PizzaDessertAdapter(response.body()!!)
                       layoutManager=manager
                       adapter=adapter
                   }
               }
            }

            override fun onFailure(call: Call<List<Burgers>>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }
}