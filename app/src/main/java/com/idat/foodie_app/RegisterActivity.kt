package com.idat.foodie_app

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RegisterActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val btnReg = findViewById<Button>(R.id.btnRegister)
        val btnCancelarR = findViewById<Button>(R.id.btnCancelarRegi)
        val txtEmailR = findViewById<EditText>(R.id.txtEmailRegis)
        val txtpasswR = findViewById<EditText>(R.id.txtPasswordRegis)
        val txtUserN = findViewById<EditText>(R.id.txtUserRegis)

        btnReg.setOnClickListener {
            val email = txtEmailR.text.toString()
            val password = txtpasswR.text.toString()
            val username = txtUserN.text.toString()
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "FOODIE: Los campos deben estar llenos para el registro", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else {
                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        db.collection("users").document(email).set(
                            hashMapOf("email" to email,
                            "nombre" to username,
                            "proveedor" to ProviderType.BASIC)
                        )
                        showMenuPrincipal(it.result?.user?.email ?: "", ProviderType.BASIC)
                    } else {
                        Toast.makeText(this, "FOODIE: Se ha producido un error autenticando al usuario", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        btnCancelarR.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showMenuPrincipal(email: String, provider: ProviderType) {
        val menuPrincipalintent = Intent(this, LoginActivity::class.java).apply {
            putExtra("email", email)
            putExtra("provider", provider.name)
        }
        startActivity(menuPrincipalintent)
    }
}