package com.idat.foodie_app.MenuRestauranteFragment

import android.content.Context
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
import com.idat.foodie_app.Utils.SelectedRestaurantId
import com.idat.foodie_app.databinding.FragmentRestBebidasBinding
import com.idat.foodie_app.databinding.FragmentRestSnacksBinding

class RestSnacksFragment : Fragment() {

    val db = FirebaseFirestore.getInstance()

    private lateinit var adapterRestPlatos: AdapterRestPlatos
    private lateinit var restPlatosList: ArrayList<RestPlatos>
    private lateinit var binding: FragmentRestSnacksBinding
    private var restSelected = SelectedRestaurantId.id

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_rest_snacks, container, false)

        binding = FragmentRestSnacksBinding.bind(view)

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

                    if (restSelected.equals("R0006") || restSelected.equals("R00011")) restSelected = "R0001"
                    else if (restSelected.equals("R0007") || restSelected.equals("R0012")) restSelected = "R0002"
                    else if (restSelected.equals("R0008") || restSelected.equals("R0013")) restSelected = "R0003"
                    else if (restSelected.equals("R0009") || restSelected.equals("R0014")) restSelected = "R0004"
                    else if (restSelected.equals("R0010") || restSelected.equals("R0015")) restSelected = "R0005"

                    if (document["idRest"] == restSelected && wallItem.categoria == "Snacks") {
                        wallItem.idRest = document.id
                        wallItem.nombre = document["nombre"].toString()
                        wallItem.precio = document["precio"].toString().toDouble()
                        wallItem.imagen = document["imagen"].toString()
                        restPlatosList.add(wallItem)
                    }
                }
                adapterRestPlatos.notifyDataSetChanged()

                if (isAdded) {
                    binding.rvRSnacksTodo.adapter = adapterRestPlatos
                    binding.rvRSnacksTodo.layoutManager = LinearLayoutManager(contexto)
                }
            }

    }

}