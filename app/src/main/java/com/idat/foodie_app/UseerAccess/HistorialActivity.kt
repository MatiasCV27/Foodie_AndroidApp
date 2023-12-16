package com.idat.foodie_app.UseerAccess

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.idat.foodie_app.R
import com.idat.foodie_app.Utils.SelectedUserStats

class HistorialActivity : AppCompatActivity() {

    private var obtenerGmail = SelectedUserStats.emailUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historial)

        val cerrarFaq: ImageView = findViewById(R.id.cerrarFaq)
        val setEmail = findViewById<TextView>(R.id.txtObtenerEmail)

        setEmail.text = obtenerGmail

        cerrarFaq.setOnClickListener {
            finish()
        }
    }
}