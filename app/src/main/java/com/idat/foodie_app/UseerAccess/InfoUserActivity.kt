package com.idat.foodie_app.UseerAccess

import androidx.appcompat.app.AppCompatActivity
import com.idat.foodie_app.R
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.firestore.FirebaseFirestore
import com.idat.foodie_app.Adaptadores.AdapterHistorialItems
import com.idat.foodie_app.Modelos.HistorialItems
import com.idat.foodie_app.Utils.SelectedUserStats
import com.idat.foodie_app.databinding.ActivityHistorialBinding

class InfoUserActivity : AppCompatActivity() {
    
    private var obtenerGmail = SelectedUserStats.emailUser
    val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_user)

        val cerrarFaq: ImageView = findViewById(R.id.cerrarInfoUser)

        cerrarFaq.setOnClickListener {
            finish()
        }

        obtenerDatos()

    }

    private fun obtenerDatos(){
        db.collection("users").get()
            .addOnSuccessListener { result ->
                if (result.isEmpty) {
                } else {
                    for (document in result) {
                        if (document["email"] == obtenerGmail) {
                            var nombre = findViewById<TextView>(R.id.info_name)
                            var email = findViewById<TextView>(R.id.info_email)
                            var telefono = findViewById<TextView>(R.id.info_telefono)
                            var fNac = findViewById<TextView>(R.id.info_fechaNacimiento)
                            var ubicacion = findViewById<TextView>(R.id.info_ubicacion)
                            nombre.text = document["nombre"].toString()
                            email.text = document["email"].toString()
                            telefono.text = document["telefono"].toString()
                            fNac.text = document["nacimiento"].toString()
                            ubicacion.text = document["ubicacion"].toString()
                        }
                    }
                }
            }
    }
}