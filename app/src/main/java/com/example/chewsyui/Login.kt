package com.example.chewsyui

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.chewsyui.R.id.registerNow
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    private lateinit var editTextEmail: TextInputEditText
    private lateinit var editTextPassword: TextInputEditText
    private lateinit var buttonLog: Button
    private lateinit var auth: FirebaseAuth;
    private lateinit var progressBar: ProgressBar
    private lateinit var textView: TextView

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        editTextEmail = findViewById(R.id.email)
        auth = FirebaseAuth.getInstance()
        editTextPassword = findViewById(R.id.password)
        buttonLog = findViewById(R.id.btn_login)
        textView = findViewById(registerNow)
        textView.setOnClickListener {
            val intent = Intent(applicationContext, Login::class.java)
            startActivity(intent)
            finish()



            buttonLog.setOnClickListener()
            {
                val email: String = editTextEmail.text.toString()
                val password: String = editTextPassword.text.toString()

                if (email.isEmpty()) {
                    Toast.makeText(this, "Enter email", Toast.LENGTH_SHORT).show()

                }
                if (password.isEmpty()) {
                    Toast.makeText(this, "Enter password", Toast.LENGTH_SHORT).show()

                }
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener() { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(
                                getApplicationContext(),
                                "Login Successful.",
                                Toast.LENGTH_SHORT
                            ).show()
                            val intent = Intent(
                                applicationContext, MainActivity::class.java)
                                        startActivity (intent)
                                        finish ()
                        } else {
                            Toast.makeText(
                                baseContext,
                                "Authentication failed.",
                                Toast.LENGTH_SHORT,
                            ).show()
                        }
                    }
            }
        }
    }
}

