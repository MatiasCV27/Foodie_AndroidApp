package com.idat.foodie_app.UseerAccess

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.idat.foodie_app.MenuPrincipal
import com.idat.foodie_app.R

class AyudaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ayuda)
        val cerrarFaq: ImageView = findViewById(R.id.cerrarFaq)
        val btnEnviarAyuda = findViewById<Button>(R.id.btnMandarAyuda)
        val txtNombre = findViewById<TextInputEditText>(R.id.txtNombreHelp)
        val txtCorreo = findViewById<TextInputEditText>(R.id.txtCorreoHelp)
        val txtAsunto = findViewById<TextInputEditText>(R.id.txtAsuntoHelp)
        val txtDescrip = findViewById<TextInputEditText>(R.id.txtDescipHelp)

        btnEnviarAyuda.setOnClickListener {
            val nombre = txtNombre.text.toString()
            val correo = txtCorreo.text.toString()
            val asunto = txtAsunto.text.toString()
            val descrip = txtDescrip.text.toString()

            if (nombre.isEmpty() || correo.isEmpty() || asunto.isEmpty() || descrip.isEmpty()) {
                Toast.makeText(this, "FOODIE: Los campos deben estar llenos para el acceso", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "FOODIE: El mensaje se envio con exito!", Toast.LENGTH_SHORT).show()
            }
        }

        cerrarFaq.setOnClickListener {
            finish()
        }

    }
}