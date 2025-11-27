package com.example.a113_4b_25092025

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RegistrarUsuarioActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_usuario)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        val nombreEditText = findViewById<EditText>(R.id.inputNombre)
        val emailEditText = findViewById<EditText>(R.id.inputEmailRegistro)
        val passwordEditText = findViewById<EditText>(R.id.inputPasswordRegistro)
        val botonRegistrar = findViewById<Button>(R.id.botonRegistrar)

        botonRegistrar.setOnClickListener {
            val nombre = nombreEditText.text.toString().trim()
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (nombre.isEmpty() || email.isEmpty() || password.isEmpty()) {
                showAlert("Error", "Por favor, complete todos los campos.")
                return@setOnClickListener
            }

            if (password.length < 6) {
                showAlert("Error", "La contraseña debe tener al menos 6 caracteres.")
                return@setOnClickListener
            }

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val userId = auth.currentUser?.uid
                        if (userId != null) {
                            val user = hashMapOf(
                                "nombre" to nombre,
                                "email" to email
                            )
                            db.collection("users").document(userId)
                                .set(user)
                                .addOnSuccessListener { 
                                    // Show success dialog and navigate ONLY after clicking "Aceptar"
                                    AlertDialog.Builder(this)
                                        .setTitle("Registro Exitoso")
                                        .setMessage("¡Tu cuenta ha sido creada exitosamente!")
                                        .setCancelable(false) // User must click button
                                        .setPositiveButton("Aceptar") { dialog, _ ->
                                            dialog.dismiss()
                                            // Navigate to Login screen
                                            val intent = Intent(this, MainActivity::class.java)
                                            startActivity(intent)
                                            finish() // Finish this activity so user can't go back
                                        }
                                        .show()
                                }
                                .addOnFailureListener { e ->
                                    showAlert("Error", "No se pudieron guardar los datos del usuario: ${e.localizedMessage}")
                                }
                        }
                    } else {
                        showAlert("Error de Registro", "No se pudo crear la cuenta. Es posible que el correo ya esté en uso. ${task.exception?.localizedMessage}")
                    }
                }
        }

        val botonIniciarSesion = findViewById<Button>(R.id.botonIniciarSesionRegistro)
        botonIniciarSesion.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showAlert(title: String, message: String) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Aceptar", null)
            .show()
    }
}