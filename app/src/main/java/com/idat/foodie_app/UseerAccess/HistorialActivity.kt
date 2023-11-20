package com.idat.foodie_app.UseerAccess

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.idat.foodie_app.R

class HistorialActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historial)

        val cerrarFaq: ImageView = findViewById(R.id.cerrarFaq)

        cerrarFaq.setOnClickListener {
            finish()
        }
    }
}