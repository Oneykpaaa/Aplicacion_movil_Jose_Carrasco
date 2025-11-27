package com.example.a113_4b_25092025

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class RecuperarSenaActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recuperar_sena)

        auth = FirebaseAuth.getInstance()

        val emailEditText = findViewById<EditText>(R.id.inputEmailRecuperar)
        val botonRecuperar = findViewById<Button>(R.id.botonRecuperar)
        botonRecuperar.setOnClickListener {
            val email = emailEditText.text.toString().trim()

            if (email.isEmpty()) {
                AlertDialog.Builder(this@RecuperarSenaActivity)
                    .setTitle("Error")
                    .setMessage("Por favor, ingrese su correo electrónico.")
                    .setPositiveButton("Aceptar") { d, _ -> d.dismiss() }
                    .show()
                return@setOnClickListener
            }

            auth.sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        AlertDialog.Builder(this@RecuperarSenaActivity)
                            .setTitle("Recuperar Contraseña")
                            .setMessage("Se ha enviado un correo de recuperación a su dirección de correo electrónico.")
                            .setPositiveButton("Aceptar") { d, _ -> d.dismiss() }
                            .show()
                    } else {
                        AlertDialog.Builder(this@RecuperarSenaActivity)
                            .setTitle("Error")
                            .setMessage("No se pudo enviar el correo de recuperación. Verifique que el correo sea correcto y esté registrado.")
                            .setPositiveButton("Aceptar") { d, _ -> d.dismiss() }
                            .show()
                    }
                }
        }

        val botonVolver = findViewById<Button>(R.id.botonVolverRecuperar)
        botonVolver.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}