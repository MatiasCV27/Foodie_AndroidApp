package com.idat.foodie_app.UseerAccess

import androidx.appcompat.app.AppCompatActivity
import com.idat.foodie_app.R
import android.os.Bundle
import android.widget.ImageView

class InfoUserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_user)

        val cerrarFaq: ImageView = findViewById(R.id.cerrarInfoUser)

        cerrarFaq.setOnClickListener {
            finish()
        }

    }
}