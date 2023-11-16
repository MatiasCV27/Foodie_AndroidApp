package com.idat.foodie_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View;
import android.widget.Button;
import android.content.Intent;


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val btnLogin: Button = findViewById(R.id.btnLogin)

        btnLogin.setOnClickListener {
            // Crear un Intent para iniciar ActividadDestino
            val intent = Intent(this@LoginActivity, MenuPrincipal::class.java)
            // Iniciar la nueva actividad
            startActivity(intent)
        }

    }
}