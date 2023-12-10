package com.idat.foodie_app.MenuCatRestFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.idat.foodie_app.Adaptadores.AdapterRestaurantes
import com.idat.foodie_app.Modelos.Restaurantes
import com.idat.foodie_app.R
import com.idat.foodie_app.databinding.FragmentCatRestChifaBinding
import com.idat.foodie_app.databinding.FragmentCatRestParrillasBinding
import com.idat.foodie_app.databinding.FragmentCatRestPollosBinding


class CatRestChifaFragment : Fragment() {

    val db = FirebaseFirestore.getInstance()

    private lateinit var adapterRest: AdapterRestaurantes
    private lateinit var restList: ArrayList<Restaurantes>
    private lateinit var binding: FragmentCatRestChifaBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cat_rest_chifa, container, false)

        binding = FragmentCatRestChifaBinding.bind(view)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        llamarRecyclerView()
    }

    private fun llamarRecyclerView() {
        restList = ArrayList()
        adapterRest = AdapterRestaurantes(restList)
        db.collection("restaurantes").get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val wallItem = document.toObject(Restaurantes::class.java)
                    if (wallItem.categoria == "Chifa") {
                        wallItem.idRest = document.id
                        wallItem.nombre = document["nombre"].toString()
                        wallItem.descripcion = document["descripcion"].toString()
                        wallItem.imagen = document["imagen"].toString()
                        restList.add(wallItem)
                    }
                }
                adapterRest.notifyDataSetChanged()
                binding.rvRestChifa.adapter = adapterRest
                binding.rvRestChifa.layoutManager = LinearLayoutManager(requireContext())
            }
    }

}