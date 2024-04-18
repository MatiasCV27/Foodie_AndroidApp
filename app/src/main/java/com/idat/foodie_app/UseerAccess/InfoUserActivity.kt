package com.idat.foodie_app.UseerAccess

import androidx.appcompat.app.AppCompatActivity
import com.idat.foodie_app.R
import android.os.Bundle
import android.widget.ImageView
import com.google.firebase.firestore.FirebaseFirestore
import com.idat.foodie_app.Adaptadores.AdapterHistorialItems
import com.idat.foodie_app.Modelos.HistorialItems
import com.idat.foodie_app.Utils.SelectedUserStats
import com.idat.foodie_app.databinding.ActivityHistorialBinding

class InfoUserActivity : AppCompatActivity() {
    
    private var obtenerGmail = SelectedUserStats.emailUser
    val db = FirebaseFirestore.getInstance()

    private lateinit var adapterHist: AdapterHistorialItems
    private lateinit var historiaList: ArrayList<HistorialItems>
    private lateinit var binding: ActivityHistorialBinding
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
        historiaList = ArrayList()
        adapterHist = AdapterHistorialItems(historiaList)
        db.collection("users").get()
            .addOnSuccessListener { document ->

                //val wallItem = document.toObject(HistorialItems::class.java)
                //if (document["email"] == obtenerGmail) {
                //    wallItem.nombre = document["nombre"].toString()
                //    wallItem.precios = document["precios"].toString()
                //    wallItem.fecha = document["fecha"].toString()
                //    wallItem.precioTotal = document["pTotal"].toString()
                //    historiaList.add(wallItem)


        }
    }

}