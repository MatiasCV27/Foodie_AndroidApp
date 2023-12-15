package com.idat.foodie_app.NavbarFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.idat.foodie_app.Adaptadores.AdapterCarritoItems
import com.idat.foodie_app.R
import com.idat.foodie_app.Utils.SelectedPlatosItems

class ShoppingCartFragment : Fragment() {

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

        if (carritoAdapter.itemCount > 0) {
            pTotal.text = "S/ " + precioTotal.toString()
        } else {
            SelectedPlatosItems.precioTotal = 0.0
            pTotal.text = "S/ 0.00"
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