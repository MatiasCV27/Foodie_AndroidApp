package com.idat.foodie_app.UseerAccess

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.idat.foodie_app.Adaptadores.AdapterHistorialItems
import com.idat.foodie_app.Adaptadores.AdapterRestPlatos
import com.idat.foodie_app.Adaptadores.AdapterRestaurantes
import com.idat.foodie_app.Modelos.HistorialItems
import com.idat.foodie_app.Modelos.RestPlatos
import com.idat.foodie_app.Modelos.Restaurantes
import com.idat.foodie_app.R
import com.idat.foodie_app.Utils.SelectedUserStats
import com.idat.foodie_app.databinding.ActivityHistorialBinding
import com.idat.foodie_app.databinding.FragmentCatRestPollosBinding

class HistorialActivity : AppCompatActivity() {

    val db = FirebaseFirestore.getInstance()

    private lateinit var adapterHist: AdapterHistorialItems
    private lateinit var historiaList: ArrayList<HistorialItems>
    private lateinit var binding: ActivityHistorialBinding

    private var obtenerGmail = SelectedUserStats.emailUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historial)

        binding = ActivityHistorialBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cerrarFaq: ImageView = findViewById(R.id.cerrarFaq)
        val setEmail = findViewById<TextView>(R.id.txtObtenerEmail)

        setEmail.text = obtenerGmail

        cerrarFaq.setOnClickListener {
            finish()
        }

        llamarRecyclerView()
    }

    private fun llamarRecyclerView() {
        historiaList = ArrayList()
        adapterHist = AdapterHistorialItems(historiaList)
        db.collection("histPagos").get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val wallItem = document.toObject(HistorialItems::class.java)
                    if (document["email"] == obtenerGmail) {
                        wallItem.nombres = document["nombres"].toString()
                        wallItem.precios = document["precios"].toString()
                        wallItem.fecha = document["fecha"].toString()
                        wallItem.precioTotal = document["pTotal"].toString()
                        historiaList.add(wallItem)
                    }
                }
                adapterHist.notifyDataSetChanged()
                binding.rvHistorialItems.adapter = adapterHist
                binding.rvHistorialItems.layoutManager = LinearLayoutManager(this)
            }
    }
}