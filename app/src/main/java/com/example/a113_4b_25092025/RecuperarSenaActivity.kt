package com.example.a113_4b_25092025

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class RecuperarSenaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recuperar_sena)

        val botonRecuperar = findViewById<Button>(R.id.botonRecuperar)
        botonRecuperar.setOnClickListener {
            AlertDialog.Builder(this@RecuperarSenaActivity)
                .setTitle("Recuperar Contraseña")
                .setMessage("Se ha enviado un correo de recuperación a su dirección de correo electrónico.")
                .setPositiveButton("Aceptar"){d,_ -> d.dismiss()}
                .show()
        }

        val botonVolver = findViewById<Button>(R.id.botonVolverRecuperar)
        botonVolver.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}