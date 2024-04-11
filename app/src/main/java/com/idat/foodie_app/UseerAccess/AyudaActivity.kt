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

    private lateinit var btnayuda: Button
    private lateinit var nombre: TextInputEditText
    private lateinit var asunto: TextInputEditText
    private lateinit var mensaje: TextInputEditText

    private val correoFijo = "FoodieSoport@gmail.com" // Correo electrónico fijo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ayuda)
        val cerrarFaq: ImageView = findViewById(R.id.cerrarFaq)
        btnayuda = findViewById(R.id.btnMandarAyuda)
        nombre = findViewById(R.id.txtNombreHelp)
        asunto = findViewById(R.id.txtAsuntoHelp)
        mensaje = findViewById(R.id.txtDescipHelp)

        btnayuda.setOnClickListener {
            val nombreText = nombre.text.toString()
            val asuntoText = asunto.text.toString()
            val mensajeText = mensaje.text.toString()

            when {
                nombreText.isEmpty() -> {
                    Toast.makeText(this, "Ingresa tu nombre", Toast.LENGTH_LONG).show()
                }
                asuntoText.isEmpty() -> {
                    Toast.makeText(this, "Ingresa el asunto", Toast.LENGTH_LONG).show()
                }
                mensajeText.isEmpty() -> {
                    Toast.makeText(this, "Ingresa un mensaje", Toast.LENGTH_LONG).show()
                }
                else -> {
                    val intent = Intent(Intent.ACTION_SEND)
                    intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(correoFijo))
                    intent.putExtra(Intent.EXTRA_SUBJECT, asuntoText)
                    intent.putExtra(Intent.EXTRA_TEXT, "Nombre: $nombreText\nMensaje: $mensajeText")
                    intent.type = "message/rfc822"
                    startActivity(Intent.createChooser(intent, "Elige un cliente de correo:"))
                    finish()
                }
            }
        }

        cerrarFaq.setOnClickListener {
            finish()
        }
    }
}