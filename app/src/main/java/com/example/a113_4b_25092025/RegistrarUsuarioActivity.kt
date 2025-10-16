package com.example.a113_4b_25092025

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class RegistrarUsuarioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_usuario)

        val botonRegistrar = findViewById<Button>(R.id.botonRegistrar)
        botonRegistrar.setOnClickListener {
            AlertDialog.Builder(this@RegistrarUsuarioActivity)
                .setTitle("Registro Exitoso")
                .setMessage("¡Tu cuenta ha sido creada exitosamente!")
                .setPositiveButton("Aceptar"){d,_ -> d.dismiss()}
                .show()
        }

        val botonIniciarSesion = findViewById<Button>(R.id.botonIniciarSesionRegistro)
        botonIniciarSesion.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}