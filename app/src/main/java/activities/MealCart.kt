package activities


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hotel.R
import com.example.hotel.data.PizzaDessertAdapter
import data.Burgers
import data.CartItem
import kotlinx.coroutines.flow.internal.NoOpContinuation.context
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import utils.RetrofitInstance
import kotlin.coroutines.jvm.internal.CompletedContinuation.context

class MealCart :AppCompatActivity() {
    //private lateinit var declares private, non-nullable properties that can be initalized later
    private lateinit var recyclerView: RecyclerView
    private lateinit var manager: RecyclerView.LayoutManager
    private lateinit var adapter: RecyclerView.Adapter<*>
    private lateinit var proceedToCart: Button
    private val cartRequestCode=123



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mealcart)
    proceedToCart=findViewById(R.id.btnProceedToCart)
        manager = LinearLayoutManager(this)

//fetching data from the api
        fun getAllData() {
            //get an instance of our API SERVICE Through the retrofitInstance Object
            val apiService = RetrofitInstance.api
    //make an asynchronous call to the APi to get a list of burgers, which uses an anonymous callback to handle the response
            val call = apiService.getAllBurgers().enqueue(object : Callback<List<Burgers>> {
                override fun onResponse(
                    call: Call<List<Burgers>>,
                    response: Response<List<Burgers>>
                ) {
                    if (response.isSuccessful) {
                        //initialize our recyclerview and applying configurations within the apply block
                        //we create and set the pizzaDessertAdapter for the recyclerview
                        recyclerView = findViewById<RecyclerView>(R.id.pizzaDessertRecyclerView).apply {
                                adapter = PizzaDessertAdapter(burgers=response.body()!!){ selectedBurger-> val intent= Intent(this@MealCart, CheckOut::class.java)
                                    intent.putExtra("selectedBurger",selectedBurger)
                                    startActivityForResult(intent,cartRequestCode)
                                }
                                layoutManager = manager
                                adapter = adapter
                            }

                        proceedToCart.setOnClickListener{
                            val intent=Intent(this@MealCart, CheckOut::class.java)
                            startActivityForResult(intent,cartRequestCode)
                        }
                    }
                    fun updateProceedToCartButtonVisibiltiy() {
                        TODO("Not yet implemented")
                    }

                    fun updateAdapterButtonStates(updatedCart: ArrayList<CartItem>) {

                    }

                    override fun onActivityResult(requestCode:Int, resultcode:Int, data:Intent?){
                        super.onActivityResult(requestCode,resultcode,data)
                        val updatedCart=data?.getParcelableArrayListExtra<CartItem>("updatedCart")
                        if(updatedCart!=null){
                            updateAdapterButtonStates(updatedCart)
                        }
                        updateProceedToCartButtonVisibiltiy()
                    }
                }

                override fun onFailure(call: Call<List<Burgers>>, t: Throwable) {
                    t.printStackTrace()
                }

            })
        }
    }




    }
