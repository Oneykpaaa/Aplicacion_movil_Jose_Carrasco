package com.example.a113_4b_25092025

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        auth = Firebase.auth

        val emailEditText = findViewById<EditText>(R.id.inputEmail)
        val passwordEditText = findViewById<EditText>(R.id.inputPassword)
        val botonPrincipal = findViewById<Button>(R.id.botonPrincipal)

        botonPrincipal.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage("Por favor, ingrese correo y contraseña.")
                    .setPositiveButton("Aceptar", null)
                    .show()
                return@setOnClickListener
            }

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success
                        val user = auth.currentUser
                        AlertDialog.Builder(this)
                            .setTitle("Inicio de Sesión Exitoso")
                            .setMessage("¡Bienvenido de vuelta, ${user?.email}!")
                            .setPositiveButton("Aceptar", null)
                            .show()
                        // Aquí puedes navegar a otra actividad si lo deseas
                    } else {
                        // If sign in fails, display a message to the user.
                        AlertDialog.Builder(this)
                            .setTitle("Error de Inicio de Sesión")
                            .setMessage("Correo o contraseña incorrectos.")
                            .setPositiveButton("Aceptar", null)
                            .show()
                    }
                }
        }

        val textRegister = findViewById<TextView>(R.id.textRegister)
        textRegister.setOnClickListener {
            val intent = Intent(this, RegistrarUsuarioActivity::class.java)
            startActivity(intent)
        }

        val textForgotPassword = findViewById<TextView>(R.id.textForgotPassword)
        textForgotPassword.setOnClickListener {
            val intent = Intent(this, RecuperarSenaActivity::class.java)
            startActivity(intent)
        }
    }
}