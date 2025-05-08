package activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hotel.R
import com.example.hotel.data.CartAdapter
import com.example.hotel.utils.ConnectionManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import data.CartItem
import org.json.JSONArray
import org.json.JSONObject
import java.util.UUID


class CheckOut : AppCompatActivity() {


    lateinit var toolbar: androidx.appcompat.widget.Toolbar
    lateinit var recyclerView: androidx.recyclerview.widget.RecyclerView
    lateinit var btnPlaceOrder: Button
    lateinit var menuAdapter: CartAdapter
    lateinit var menuList: List<CartItem>
    lateinit var txtOrderingFrom:TextView
    lateinit var txtOrderingFromText:TextView
    lateinit var txtTotalCost:TextView

//will confirm to customer that order has been placed

    private fun calculateTotalCost(): Double {
        var totalCost = 0.0
        for (cartItem in menuList) {
            val itemCost = cartItem.burger?.price?. times (cartItem.quantity)?:0.0
            totalCost += itemCost
        }
        return totalCost
        }
//implementing the order number generation logic
    private fun generateOrderNumber(): Any? {
        return UUID.randomUUID().toString().substring(0,8)
    //our first 8 characters are the order number
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //initializiation of UI elements within the onCreate method
        setContentView(R.layout.activity_check_out)
        btnPlaceOrder= findViewById(R.id.btnPlaceOrder)
        toolbar=findViewById(R.id.toolBar)
        recyclerView=findViewById(R.id.recyclerViewCart)
        txtOrderingFrom= findViewById(R.id.txtOrderingFrom)
         menuList= intent.getParcelableArrayListExtra<CartItem>("cartItems")?: emptyList()
        menuAdapter= CartAdapter(this,menuList)
        recyclerView.layoutManager= LinearLayoutManager(this)
        recyclerView.adapter= menuAdapter

//calculate and display total cost
        val totalCost= calculateTotalCost()

        btnPlaceOrder.text= "Place Order ($totalCost)"



        btnPlaceOrder.setOnClickListener{
           if(ConnectionManager().checkConnectivity(this)){

               try {
                   //this defines an empty jsonArray created to representations of the cartItem
                   //initialize the JSONArray class here
                   val cartDataJson = JSONArray()
                   //this loop iterates through each cartItem object in the menuList
                   for (cartItem in menuList) {
                       //in the loop  a new JSON object is created for each Cart Item
                       val cartItemJson = JSONObject()// org.json.jsonObject
                       //the .put adds key-value pairs to the itemJsonObject
                       //mapping the property names to their corresponding values from the cartItem object
                       cartItemJson.put("id", cartItem.burger.id)
                       cartItemJson.put("name", cartItem.burger.name)
                       cartItemJson.put("price", cartItem.burger.price)
                       cartItemJson.put("quantity", cartItem.quantity)
                       //this itemJsonObject is then added to the cartDataJson array
                       cartDataJson.put(cartItemJson)
                   }
                   //get the user id from firebase authentication
                   val currentUser = FirebaseAuth.getInstance().currentUser
                   val userId = currentUser?.uid
                   if (userId == null) {

                       Toast.makeText(this, "User not logged in", Toast.LENGTH_SHORT).show()

                   }

               val totalCostValue= calculateTotalCost()
                   val database= FirebaseDatabase.getInstance()
                    val ordersRef= database.getReference("orders")
                   val orderId= UUID.randomUUID().toString()
                   val orderNumber= generateOrderNumber()
                    val itemsMap= mutableMapOf<String, Any>()
                        for (i in 0 until cartDataJson.length()){
                            val itemJson= cartDataJson.getJSONObject(i)
                            val itemId= UUID.randomUUID().toString()
                            itemsMap[itemId]= mapOf(
                                "burger_name" to itemJson.getString("name"),
                                "quantity" to itemJson.getInt("quantity"),
                                "item_cost" to itemJson.getDouble("price") *itemJson.getInt("quantity")

                            )

                        }
                   val orderData= mapOf("user_id" to userId,
                            "order_number" to orderNumber,
                            "total_cost" to totalCostValue,
                            "items" to itemsMap,
                            "status" to "placed",
                            "timestamp" to System.currentTimeMillis()
                   )
                   ordersRef.child(orderId).setValue(orderData).addOnSuccessListener{

                       Toast.makeText(this, "Order Placed Successfully!",Toast.LENGTH_SHORT).show()
                       val intent= Intent(this, OrderPlacedSuccessfuly::class.java)
                       startActivity(intent)
                       finishAffinity()
                   }
                       .addOnFailureListener{
                           Toast.makeText(this, "Error placing order!", Toast.LENGTH_SHORT).show()

                       }

                   }catch (e:Exception){
                   Toast.makeText(this, "No internet Connection", Toast.LENGTH_SHORT).show()
                   }
               //other functions

               }
           }
        }
    override fun onBackPressed(){
        AlertDialog.Builder(this)
            .setTitle("Add More Items?")
            .setMessage("Do you want to add more items or Continue with Current List?")
            .setPositiveButton("Add to Cart"){
                dialog, which->
                setResult(Activity.RESULT_OK)
                super.onBackPressed()
            }
            .setNegativeButton("Continue with Order"){
                dialog, which->
                dialog.dismiss()
            }
            .setNeutralButton("Cancel"){
                dialog, which->
                dialog.dismiss()
            }
            .show()
    }

    }
