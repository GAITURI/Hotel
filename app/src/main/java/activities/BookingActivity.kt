package activities

import android.content.Intent
import android.graphics.Movie
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
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