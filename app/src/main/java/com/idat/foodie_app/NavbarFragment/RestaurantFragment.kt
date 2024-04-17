package com.idat.foodie_app.NavbarFragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.textfield.TextInputEditText
import com.idat.foodie_app.Adaptadores.AdapterRestaurantes
import com.idat.foodie_app.MenuRestauranteFragment.*
import com.idat.foodie_app.Modelos.Restaurantes
import com.idat.foodie_app.R

class RestaurantFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_restaurant, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val ft = parentFragmentManager.beginTransaction()
        ft.replace(R.id.frameMenuRestaurant, RestTodoFragment())
        ft.commit()
    }
}