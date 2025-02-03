package activities

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.hotel.databinding.ActivitySignupBinding
import com.example.hotel.R
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private lateinit var etEmail: EditText
    private lateinit var etPass: EditText
    private lateinit var etConfpass: EditText
    private lateinit var btnSignUp: Button
    private lateinit var btnLogin: Button
    //a firebase authentication object
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
setTheme(R.style.AppTheme)

        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //instead of using findViewBy id(repeatedly)
        //viewbinding automatucally generates a binding class for each xml layout file
        //binding class has references to all the views in the layout file

        //working on bindings
        etEmail = findViewById(R.id.etemail)
        etPass = findViewById(R.id.etPassword)
        etConfpass = findViewById(R.id.confPassword)
        btnSignUp = findViewById(R.id.SignUpButton)

        //initialising auth object
        auth = Firebase.auth
        btnLogin = findViewById(R.id.LoginButton)
        btnLogin.setOnClickListener {
            val intent = Intent(this, BookingActivity::class.java)
            startActivity(intent)
        }
        btnSignUp.setOnClickListener {
            signUpUser()
        }
    }

    private fun signUpUser() {
        val email = etEmail.text.toString()
        val pass = etPass.text.toString()
        val confirmPassword = etConfpass.text.toString()


        //handle exceptions
        if (email.isBlank() || pass.isBlank() || confirmPassword.isBlank()) {
            Toast.makeText(this, "Fill all Valid fields", Toast.LENGTH_SHORT).show()
            return
        }
        if (pass != confirmPassword) {
            Toast.makeText(this, "Password does not match", Toast.LENGTH_SHORT).show()
            return
        }
        if (!isNetworkConnected()) {
            showErrorMessage("No internet connection")
            return
        }
//if all credentials are correct
        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                Toast.makeText(this, "Signed up successfully", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Failed to create", Toast.LENGTH_SHORT).show()
            }


        }
    }

    private fun isNetworkConnected():Boolean{
        val connectivityManager=getSystemService(Context.CONNECTIVITY_SERVICE)as ConnectivityManager
        val activeNetwork=connectivityManager.activeNetworkInfo
        return activeNetwork!=null && activeNetwork.isConnectedOrConnecting
    }
    private fun showErrorMessage(message: String) {
        Toast.makeText(this, "Error on Network", Toast.LENGTH_SHORT).show()
    }





}
