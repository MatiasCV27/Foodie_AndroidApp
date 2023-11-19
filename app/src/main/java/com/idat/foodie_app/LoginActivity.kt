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
        val txtEmail = findViewById<TextInputEditText>(R.id.txtEmail)
        val txtpassw = findViewById<TextInputEditText>(R.id.txtPassword)

        btnIniciarS.setOnClickListener {
            val intent = Intent(this, MenuPrincipal::class.java)
            startActivity(intent)
        }

        btnIniciarG.setOnClickListener{
            val mensaje = "FOODIE: Usted a iniciado sesión con Google!"
            val duracion = Toast.LENGTH_SHORT
            val toast = Toast.makeText(applicationContext, mensaje, duracion)
            toast.show()
        }
    }
}