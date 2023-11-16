package com.idat.foodie_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnIniciarS = findViewById<Button>(R.id.btnSesion)
        val btnIniciarG = findViewById<Button>(R.id.btnSGoogle)
        val txtEmail = findViewById<TextInputEditText>(R.id.txtEmail)
        val txtpassw = findViewById<TextInputEditText>(R.id.txtPassword)

        //btnIniciarG.setOnClickListener(
        //    if (txtEmail.getText().isEmpty() && txtpassw.getText().isEmpty()) {
        //
        //    }
        //)
    }
}