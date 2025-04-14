package activities

import android.content.Intent
import android.os.Bundle
import android.text.Layout
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hotel.R

class OrderPlacedSuccessfuly : AppCompatActivity(){
        lateinit var orderPlacedSuccessfuly: OrderPlacedSuccessfuly
        lateinit var btnOkay:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_placed_successfuly)
        orderPlacedSuccessfuly= findViewById(R.id.main)
        btnOkay= findViewById(R.id.btnOkaySuccess)

        btnOkay.setOnClickListener{
            val intent= Intent(this, BookingActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }
        }
    override fun onBackPressed() {
        super.onBackPressed()
        Toast.makeText(this@OrderPlacedSuccessfuly, "Press Ok for Main Menu", Toast.LENGTH_SHORT).show()
    }
    }
