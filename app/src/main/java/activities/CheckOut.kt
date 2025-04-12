package activities

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.menu.MenuAdapter
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.toolbox.Volley
import com.example.hotel.R
import com.example.hotel.data.CartAdapter
import com.example.hotel.utils.ConnectionManager
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import data.CartItem

class CheckOut : AppCompatActivity() {

    lateinit var toolbar: androidx.appcompat.widget.Toolbar
    lateinit var recyclerView: androidx.recyclerview.widget.RecyclerView
    lateinit var btnPlaceOrder: Button
    lateinit var menuAdapter: CartAdapter
    lateinit var menuList: List<CartItem>
    lateinit var txtOrderingFrom:TextView
    lateinit var progressLayout: RelativeLayout
    lateinit var txtOrderingFromText:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_check_out)
        btnPlaceOrder= findViewById(R.id.btnPlaceOrder)
        toolbar=findViewById(R.id.toolBar)
        recyclerView=findViewById(R.id.recyclerViewCart)
        txtOrderingFrom= findViewById(R.id.txtOrderingFrom)
        menuList= mutableListOf()
        menuAdapter= CartAdapter(this,menuList)
        recyclerView.layoutManager= LinearLayoutManager(this)
        recyclerView.adapter= menuAdapter




        btnPlaceOrder.setOnClickListener{
           if(ConnectionManager().checkConnectivity(this)){
               progressLayout.visibility= View.VISIBLE

               try{
                   //this defines an empty jsonArray created to representations of the cartItem
                   val cartDataJson= JsonArray()
                   //this loop iterates through each cartItem object in the menuList
                   for(cartItem in menuList){
                       //in the loop  a new JSON object is created for each Cart Item
                       val cartItemJson= JsonObject()
                       //the .addProperty adds key-value pairs to the itemJsonObject
                       //mapping the property names to their corresponding values from the cartItem object
                       cartItemJson.addProperty("id",cartItem.burger.id)
                       cartItemJson.addProperty("name",cartItem.burger.name)
                       cartItemJson.addProperty("price",cartItem.burger.price)
                       cartItemJson.addProperty("quantity",cartItem.quantity)
                       //this itemJsonObject is then added to the cartDataJson array
                       cartDataJson.add(cartItemJson)
                   }
                   val sendOrder=JsonObject()
                   sendOrder.addProperty("user_id","0")
                    sendOrder.addProperty("total_cost","totalCost")
                   sendOrder.add("food",cartDataJson)
                     val queue= Volley.newRequestQueue(this)
                   }
               }
           }
        }
    }
}