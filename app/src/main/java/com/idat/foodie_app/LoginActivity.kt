package com.idat.foodie_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val btnIniciarS = findViewById<Button>(R.id.btnSesion)
        val btnIniciarG = findViewById<Button>(R.id.btnSGoogle)
        val btnReistro = findViewById<Button>(R.id.btnRegistrase)
        val txtEmail = findViewById<TextInputEditText>(R.id.txtEmail)
        val txtpassw = findViewById<TextInputEditText>(R.id.txtPassword)

        btnIniciarS.setOnClickListener {
            val email = txtEmail.text.toString()
            val password = txtpassw.text.toString()
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "FOODIE: Los campos deben estar llenos para el acceso", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val intent = Intent(this, MenuPrincipal::class.java)
            startActivity(intent)
        }

        btnReistro.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        btnIniciarG.setOnClickListener{
            val mensaje = "FOODIE: Esta funcionalidad estara disponible pronto!"
            val duracion = Toast.LENGTH_SHORT
            val toast = Toast.makeText(applicationContext, mensaje, duracion)
            toast.show()
        }
    }
}