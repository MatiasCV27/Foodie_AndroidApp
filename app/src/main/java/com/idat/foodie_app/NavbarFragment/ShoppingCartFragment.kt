package com.idat.foodie_app.NavbarFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.idat.foodie_app.Adaptadores.AdapterCarritoItems
import com.idat.foodie_app.Modelos.HistorialItems
import com.idat.foodie_app.R
import com.idat.foodie_app.Utils.SelectedPlatosItems
import com.idat.foodie_app.Utils.SelectedUserStats
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ShoppingCartFragment : Fragment() {

    private val db = FirebaseFirestore.getInstance()
    private var obtenerGmail = SelectedUserStats.emailUser
    private val nombres = SelectedUserStats.nombres
    private val precios = SelectedUserStats.precios
    private lateinit var recyclerView: RecyclerView
    private lateinit var carritoAdapter: AdapterCarritoItems
    private val precioTotal = SelectedPlatosItems.precioTotal

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_shopping_cart, container, false)

        recyclerView = view.findViewById(R.id.rvCarritosItems)
        carritoAdapter = AdapterCarritoItems(ArrayList())

        recyclerView.adapter = carritoAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        agregarElementoAlCarrito()

        val pTotal = view.findViewById<TextView>(R.id.txtPrecioTotal)
        val pagar = view.findViewById<Button>(R.id.btnPagarProductos)

        if (carritoAdapter.itemCount > 0) {
            pTotal.text = "S/ " + precioTotal.toString()
        } else {
            SelectedPlatosItems.precioTotal = 0.0
            pTotal.text = "S/ 0.00"
        }

        pagar.setOnClickListener {
            val fechaActual = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date()
            )

            db.collection("ventas").add(
                hashMapOf("email" to obtenerGmail,
                "fecha" to fechaActual,
                "pTotal" to precioTotal.toString(),
                "nombres" to nombres,
                "precios" to precios)
            ).addOnSuccessListener {
                SelectedUserStats.nombres = ""
                SelectedUserStats.precios = ""
                Toast.makeText(requireContext(), "FOODIE: Pago Realizado con exito", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(requireContext(), "FOODIE: Error al hacer el pago", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    private fun agregarElementoAlCarrito() {
        SelectedPlatosItems.items.forEach { item ->
            carritoAdapter.addItem(item)
        }
        SelectedPlatosItems.clearItems()
    }

}