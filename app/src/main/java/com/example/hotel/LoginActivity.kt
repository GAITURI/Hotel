package com.example.hotel

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Message
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.hotel.databinding.ActivityLoginBinding
import com.example.hotel.ui.theme.BookingActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var btnLogin: Button
    private lateinit var lgnEmail: EditText
    private lateinit var lgnPassword:EditText
    private lateinit var auth: FirebaseAuth
    private lateinit var btnRegister: Button
    private lateinit var progressBar: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lgnEmail = findViewById(R.id.userEmail)
        lgnPassword = findViewById(R.id.userPassword)
        btnLogin = findViewById(R.id.LoginButton)
        btnRegister = findViewById(R.id.RegisterButton)
        //initalize firebase auth
        auth = FirebaseAuth.getInstance()
        btnRegister?.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
            finish()
        }
        btnLogin.setOnClickListener {
            login()

        }

    }

        private fun login() {
            val email = lgnEmail.text.toString()
            val pass = lgnPassword.text.toString()
            //calling signInWithEmailAndPassword(email,pass)

            if (!validateInputs(email, pass)) {
                return
            }
            if (!isNetworkConnected()) {
                showErrorMessage("No internet connection")
                return
            }
            //function using firebase auth object
            //on succesful response display a toast

            auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this) {
                if (it.isSuccessful){
                    val intent= Intent(this, BookingActivity::class.java)
                    startActivity(intent)
                    finish()
                }else{
                    try{
                        throw it.exception!!
                    } catch (e: FirebaseAuthInvalidUserException){
                        showErrorMessage("Invalid username or password")
                    }catch (e: FirebaseAuthInvalidCredentialsException){
                        showErrorMessage("An error occurred during authentication")
                    }
                }

                }
                }
private fun validateInputs(email: String, password: String): Boolean {
    if (email.isEmpty() || password.isEmpty()) {
        Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show()
        return false
    }
    return true
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