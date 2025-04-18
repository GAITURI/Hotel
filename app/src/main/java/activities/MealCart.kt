package activities


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
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
private val checkOutResultLauncher= registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
    if (result.resultCode == Activity.RESULT_OK) {
        val data: Intent? = result.data
        val updatedCart = data?.getParcelableArrayListExtra<CartItem>("updatedCart")
        if (updatedCart != null) {
            updateCartItems(updatedCart)
            updateAdapterButtonStates(updatedCart)

        }

    }

}
    private fun updateCartItems(updatedCart: ArrayList<CartItem>) {
        for(updatedItem in updatedCart){
          val existingItemIndex= cartItems.indexOfFirst{it.productId==updatedItem.productId}
            if(existingItemIndex != -1){
                //update existing item
                cartItems[existingItemIndex]=updatedItem
            }else{
                cartItems.add(updatedItem)
            }
        }
        cartItems.removeAll{item-> updatedCart.none{it.productId==item.productId}}}


    private fun updateAdapterButtonStates(updatedCart: ArrayList<CartItem>) {
        (recyclerView.adapter as? PizzaDessertAdapter)?.let { adapter->
            adapter.updateCartItems(updatedCart)
        }

    }


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
                                adapter = PizzaDessertAdapter(burgers = response.body()!!) {
                                    cartItem->
                                        val intent = Intent(this@MealCart, CheckOut::class.java)
                                        intent.putExtra("cartItem", cartItem)
                                       checkOutResultLauncher.launch(intent)
                                    }
                                layoutManager = manager
                                adapter = adapter
                            }

                        proceedToCart.setOnClickListener {
                            val intent = Intent(this@MealCart, CheckOut::class.java)
                            startActivityForResult(intent, cartRequestCode)
                        }
                    }
                }

                override fun onFailure(call: Call<List<Burgers>>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        }
    }




    }
