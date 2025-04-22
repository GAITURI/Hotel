package activities


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hotel.R
import com.example.hotel.data.PizzaDessertAdapter
import data.Burgers
import data.CartItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import utils.RetrofitInstance

class MealCart :AppCompatActivity() {
    //private lateinit var declares private, non-nullable properties that can be initalized later
    private lateinit var recyclerView: RecyclerView
    private lateinit var manager: RecyclerView.LayoutManager
    private lateinit var adapter: RecyclerView.Adapter<*>
    private lateinit var proceedToCart: Button
    private val cartItems=ArrayList<CartItem>()
    private val cartRequestCode=123
//this function handles starting the activity
    //we update it based on the changes made in checkout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mealcart)
        proceedToCart = findViewById(R.id.btnProceedToCart)
        manager = LinearLayoutManager(this)
        getAllData()
    }
//fetching data from the api
     private  fun getAllData() {
            //get an instance of our API SERVICE Through the retrofitInstance Object
            val apiService = RetrofitInstance.api
    //make an asynchronous call to the APi to get a list of burgers, which uses an anonymous callback to handle the response
            val call = apiService.getAllBurgers().enqueue(object : Callback<List<Burgers>> {
                override fun onResponse(call: Call<List<Burgers>>, response: Response<List<Burgers>>) {
                    if (response.isSuccessful) {
                        //initialize our recyclerview and applying configurations within the apply block
                        //we create and set the pizzaDessertAdapter for the recyclerview
                        val burgers = response.body() ?: emptyList()
                        setUpRecyclerView(burgers)
                    }else{
                        Log.e("API Error", "Response not successful: ${response.code()}")

                    }
                }
                override fun onFailure(call: Call<List<Burgers>>, t: Throwable) {
                    Log.e("API Error", "Network request failed: ${t.message}")
                }
            })

        }
        private fun setUpRecyclerView(burgers:List<Burgers>){
            adapter= PizzaDessertAdapter(burgers= burgers){ cartItem ->
                val existingItemIndex= cartItems.indexOfFirst{it.productId== cartItem.productId }
                if(existingItemIndex != -1){
                    cartItems[existingItemIndex].quantity +=1

            }else{
                cartItems.add(cartItem.copy(quantity=1))

            }
        }
            recyclerView.apply{
                layoutManager= manager
                adapter= this@MealCart.adapter
            }
            proceedToCart.setOnClickListener{
                goToCheckout()
            }
    }
    private fun goToCheckout(){
        if(cartItems.isNotEmpty()){
            val intent= Intent(this, CheckOut::class.java)
            intent.putParcelableArrayListExtra("cartItems",ArrayList(cartItems))
            startActivity(intent)
        }else{
            Toast.makeText(this, "Your cart is empty", Toast.LENGTH_SHORT).show()
        }
    }


    }
//the goToCheckout function is the only launch point
//in setting up the recycler view the lambda function is called
//the correct flow is
// 1)user clicks addtocart on an item in the recycler view
//the pizzadessertadapter click listener calls the lambda in the mealcart.kt
//the lambda updated the cartItemList
//user clicks proceed to cart
//go to checkout is  called
//checkout activity is launched with the entire cartItemsList