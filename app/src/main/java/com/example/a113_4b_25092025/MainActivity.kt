package com.example.a113_4b_25092025

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val botonPrincipal = findViewById<Button>(R.id.botonPrincipal)
        botonPrincipal.setOnClickListener {
            AlertDialog.Builder(this@MainActivity)
                .setTitle("Inicio de Sesión Exitoso")
                .setMessage("¡Bienvenido de vuelta!")
                .setPositiveButton("Aceptar"){d,_ -> d.dismiss()}
                .show()
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