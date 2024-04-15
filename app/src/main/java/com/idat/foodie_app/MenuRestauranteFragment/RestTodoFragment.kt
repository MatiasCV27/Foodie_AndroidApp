package com.idat.foodie_app.MenuRestauranteFragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.idat.foodie_app.Adaptadores.AdapterRestPlatos
import com.idat.foodie_app.Utils.SelectedRestaurantId
import com.idat.foodie_app.Modelos.RestPlatos
import com.idat.foodie_app.R
import com.idat.foodie_app.databinding.FragmentRestTodoBinding

class RestTodoFragment : Fragment() {

    val db = FirebaseFirestore.getInstance()

    private lateinit var adapterRestPlatos: AdapterRestPlatos
    private lateinit var restPlatosList: ArrayList<RestPlatos>
    private lateinit var binding: FragmentRestTodoBinding
    private var restSelected = SelectedRestaurantId.categoria

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_rest_todo, container, false)

        binding = FragmentRestTodoBinding.bind(view)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val contexto = requireContext()
        llamarRecyclerView(contexto)
    }

    private fun llamarRecyclerView(contexto: Context) {
        restPlatosList = ArrayList()
        adapterRestPlatos = AdapterRestPlatos(restPlatosList)
        db.collection("restPlatos").get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val wallItem = document.toObject(RestPlatos::class.java)

                    if ((document["categoria"] == restSelected || wallItem.categoria=="KFC")||
                        (document["categoria"] == restSelected || wallItem.categoria=="Pizza Hut")||
                        (document["categoria"] == restSelected || wallItem.categoria=="Papa John's")||
                        (document["categoria"] == restSelected || wallItem.categoria=="Chifa Hong Kong")||
                        (document["categoria"] == restSelected || wallItem.categoria=="McDonald's")||
                        (document["categoria"] == restSelected || wallItem.categoria=="Mariscos La Perla")||
                        (document["categoria"] == restSelected || wallItem.categoria=="Popeyes")||
                        (document["categoria"] == restSelected || wallItem.categoria=="KFC")||
                        (document["categoria"] == restSelected || wallItem.categoria=="La Parrilla del Gaucho")||
                        (document["categoria"] == restSelected || wallItem.categoria=="El Cangrejo Feliz")||
                        (document["categoria"] == restSelected || wallItem.categoria=="TGI Fridays")||
                        (document["categoria"] == restSelected || wallItem.categoria=="Pizzería La Rusticana")||
                        (document["categoria"] == restSelected || wallItem.categoria=="Mariscos El Puerto")||
                        (document["categoria"] == restSelected || wallItem.categoria=="Bembos")||
                        (document["categoria"] == restSelected || wallItem.categoria=="Burger King")||
                        (document["categoria"] == restSelected || wallItem.categoria=="Chifa Mandarin")||
                        (document["categoria"] == restSelected || wallItem.categoria=="Chifa San Joy Lao")||
                        (document["categoria"] == restSelected || wallItem.categoria=="Pizza Napoli")||
                        (document["categoria"] == restSelected || wallItem.categoria=="El Asador Gaúcho")||
                        (document["categoria"] == restSelected || wallItem.categoria=="Pardos Chicken")||
                        (document["categoria"] == restSelected || wallItem.categoria=="La Hacienda del Sabor")||
                        (document["categoria"] == restSelected || wallItem.categoria=="Asador Criollo")||
                        (document["categoria"] == restSelected || wallItem.categoria=="Pollos a la Leña La Granja")||
                        (document["categoria"] == restSelected || wallItem.categoria=="Antojitos de mi Tierra")||
                        (document["categoria"] == restSelected || wallItem.categoria=="Chifa Fu Hua")||
                        (document["categoria"] == restSelected || wallItem.categoria=="Parrilla Argentina El Rancho")||
                        (document["categoria"] == restSelected || wallItem.categoria=="Subway")||
                        (document["categoria"] == restSelected || wallItem.categoria=="Taco Bell Premium")||
                        (document["categoria"] == restSelected || wallItem.categoria=="La Marisquería del Pescador")) {
                        wallItem.nombre = document["nombre"].toString()
                        // Aquí accedes directamente al precio de cada plato
                        val precioValue = document.getDouble("precio")

                        if (precioValue != null) {
                            wallItem.precio = precioValue // Asignar el valor de precio
                        } else {
                            // Manejar el caso en el que precioValue sea nulo
                            wallItem.precio = 0.0 // Asignar un valor predeterminado
                        }

                        wallItem.imagen = document["imagen"].toString()
                        restPlatosList.add(wallItem)
                    }

                }
                adapterRestPlatos.notifyDataSetChanged()

                if (isAdded) {
                    binding.rvRPlatosTodo.adapter = adapterRestPlatos
                    binding.rvRPlatosTodo.layoutManager = LinearLayoutManager(contexto)
                }

            }
    }

}