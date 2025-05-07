package activities


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hotel.R
import com.example.hotel.data.PizzaDessertAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import data.Burgers
import data.CartItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import utils.RetrofitInstance
import androidx.appcompat.app.AlertDialog

class MealCart :AppCompatActivity() {
    //private lateinit var declares private, non-nullable properties that can be initalized later
    private lateinit var recyclerView: RecyclerView
    private lateinit var manager: RecyclerView.LayoutManager
    private lateinit var adapter: PizzaDessertAdapter
    private lateinit var proceedToCart: Button
    private val cartItems=ArrayList<CartItem>()
    private val cartRequestCode=123
    private lateinit var bottomNavigationView:BottomNavigationView
    private lateinit var fragmentContainer: View
//this function handles starting the activity
    //we update it based on the changes made in checkout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mealcart)
        bottomNavigationView= findViewById(R.id.bottom_navigation)
        recyclerView=findViewById(R.id.pizzaDessertRecyclerView)
        manager= LinearLayoutManager(this)
        recyclerView.layoutManager= manager

        //this refers to the instance of pizzadessertadapter created in mealcart activity
        adapter= PizzaDessertAdapter(burgers= mutableListOf()){
            cartItem ->
            val existingItemIndex= cartItems.indexOfFirst{it.burger.id == cartItem.burger.id }
            if(existingItemIndex != -1){
                cartItems[existingItemIndex].quantity +=1

            }else{
               val newCartItem = CartItem(cartItem.burger, 1)
                cartItems.add(newCartItem)

            }
        }
        recyclerView.adapter= adapter
        getAllData()
        bottomNavigationView.setOnItemSelectedListener {
         item->   when(item.itemId){
                R.id.bookingActivity ->{
                    showCancelOrderDialog()
                    true
                }
                R.id.mealCartActivity -> {
                    refreshMealCart()
                    true
                }
                R.id.checkOutActivity -> {
                    goToCheckout()
                    true
                }
                else->false

                 }
        }
    }

    private fun refreshMealCart() {
        cartItems.clear()
        getAllData()
        adapter.notifyDataSetChanged()

    }

    private fun showCancelOrderDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Cancel")
        builder.setMessage("Confirm to Cancel Order")
        builder.setPositiveButton("Yes"){
            dialog, which->
            startActivity(Intent(this,BookingActivity::class.java))
        }
    builder.setNegativeButton("No") {
        dialog, which->
        dialog.dismiss()
    }
    builder.show()
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
                            for (burger in burgers){
                                val name= burger.name?:"Unknown burger"
                                val price= burger.price ?:0
                                val description=burger.description ?:"No description available"
                                val images= burger.images ?:emptyList()
                            }
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
            //.burgers is a property(variable) within the pizzadessertadapter class
            //its a List<Burgers> that holds the actual data(the list of burger objects)that the adapter will use to populate the recyclerview
            //= burgers refers to the List<Burgers> received from the API CALL IN THE GET
            //ITS THE NEW LIST OF BURGERS THAT WE WANT TO DISPLAY
           adapter.burgers= burgers.toMutableList()
            adapter.notifyDataSetChanged()



    }
    private fun goToCheckout(){
        if(cartItems.isNotEmpty()){
            for(cartItem in cartItems){
                Log.d("MealCart","CartItem:${cartItem.burger.name}, Quantity:${cartItem.quantity}")
            //debugging:check if images list is null
                if(cartItem.burger.images== null) {
                    Log.e("MealCart", "Error:images list is null for burger: ${cartItem.burger.name}")
                }else{
                    Log.d("MealCart", "Number of images for:${cartItem.burger.images.size}")
                }
              //debugging:check if data list is nul
                if(cartItem.burger == null) {
                    Log.e("MealCart","Error: data list is null for burger:${cartItem.burger}")
                } else{
                    Log.d("MealCart", "Number of data for ${cartItem.burger.name}: ${cartItem.burger.images?.size}")
                }

            }
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