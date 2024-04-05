package com.idat.foodie_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class PreloadingActivity : AppCompatActivity() {

    private lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preloading)
        handler = Handler()
        handler.postDelayed({
            val intent = Intent(this@PreloadingActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}