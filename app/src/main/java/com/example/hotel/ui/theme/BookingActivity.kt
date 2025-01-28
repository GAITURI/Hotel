package com.example.hotel.ui.theme

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hotel.MealCart
import com.example.hotel.R
import com.example.hotel.databinding.ActivityBookingBinding

class BookingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBookingBinding
    private lateinit var cardView: CardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)

binding=ActivityBookingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cardView= findViewById(R.id.cardFood)

        cardView.setOnClickListener{
            val intent= Intent(this, MealCart::class.java)
            startActivity(intent)

        }
    }
}