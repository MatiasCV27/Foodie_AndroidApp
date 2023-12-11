package com.idat.foodie_app.MenuRestauranteFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.idat.foodie_app.Adaptadores.AdapterRestPlatos
import com.idat.foodie_app.Modelos.RestPlatos
import com.idat.foodie_app.R
import com.idat.foodie_app.databinding.FragmentRestBebidasBinding
import com.idat.foodie_app.databinding.FragmentRestPostresBinding

class RestPostresFragment : Fragment() {

    val db = FirebaseFirestore.getInstance()

    private lateinit var adapterRestPlatos: AdapterRestPlatos
    private lateinit var restPlatosList: ArrayList<RestPlatos>
    private lateinit var binding: FragmentRestPostresBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_rest_postres, container, false)

        binding = FragmentRestPostresBinding.bind(view)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        llamarRecyclerView()
    }

    private fun llamarRecyclerView() {
        restPlatosList = ArrayList()
        adapterRestPlatos = AdapterRestPlatos(restPlatosList)
        db.collection("restPlatos").get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val wallItem = document.toObject(RestPlatos::class.java)
                    if (wallItem.categoria == "Postres") {
                        wallItem.idRest = document.id
                        wallItem.nombre = document["nombre"].toString()
                        wallItem.precio = document["precio"].toString().toDouble()
                        wallItem.imagen = document["imagen"].toString()
                        restPlatosList.add(wallItem)
                    }
                }
                adapterRestPlatos.notifyDataSetChanged()
                binding.rvRPostresTodo.adapter = adapterRestPlatos
                binding.rvRPostresTodo.layoutManager = LinearLayoutManager(requireContext())
            }

    }
}