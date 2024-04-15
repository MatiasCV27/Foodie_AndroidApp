package com.idat.foodie_app.MenuRestauranteFragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
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
    private var restSelected = SelectedRestaurantId.nombres

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

        // Consulta Firestore solo para los platos del restaurante seleccionado
        db.collection("restPlatos")
            .whereEqualTo("categoria", restSelected) // Filtra por el nombre del restaurante seleccionado
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val wallItem = document.toObject(RestPlatos::class.java)

                    // Asigna los datos del documento al objeto RestPlatos
                    wallItem.nombre = document["nombre"].toString()
                    val precioValue = document.getDouble("precio")
                    if (precioValue != null) {
                        wallItem.precio = precioValue
                    } else {
                        wallItem.precio = 0.0
                    }
                    wallItem.imagen = document["imagen"].toString()

                    // Agrega el plato a la lista solo si pertenece al restaurante seleccionado
                    restPlatosList.add(wallItem)

                    // Agrega logs para verificar los datos obtenidos
                    Log.d("PlatoObtenido", "Nombre: ${wallItem.nombre}, Precio: ${wallItem.precio}, Imagen: ${wallItem.imagen}")
                }
                adapterRestPlatos.notifyDataSetChanged()

                // Configura el RecyclerView solo si el fragmento está añadido a la actividad
                if (isAdded) {
                    binding.rvRPlatosTodo.adapter = adapterRestPlatos
                    binding.rvRPlatosTodo.layoutManager = LinearLayoutManager(contexto)
                }

                // Muestra un Toast indicando que se seleccionó un restaurante
                val mensaje = "Seleccionaste $restSelected"
                Toast.makeText(contexto, mensaje, Toast.LENGTH_SHORT).show()
            }
    }

}