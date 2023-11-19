package com.idat.foodie_app.NavbarFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.idat.foodie_app.MenuRestauranteFragment.*
import com.idat.foodie_app.R

class RestaurantFragment : Fragment() {

    private lateinit var chipTodo: Chip
    private lateinit var chipEspecialidad: Chip
    private lateinit var chipPlatos: Chip
    private lateinit var chipSnacks: Chip
    private lateinit var chipBebidas: Chip
    private lateinit var chipPostres: Chip

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_restaurant, container, false)

        chipTodo = view.findViewById(R.id.chipRestTodo)
        chipEspecialidad = view.findViewById(R.id.chipRestEspecialidad)
        chipPlatos = view.findViewById(R.id.chipRestPlatos)
        chipSnacks = view.findViewById(R.id.chipRestSnacks)
        chipBebidas = view.findViewById(R.id.chipRestBebidas)
        chipPostres = view.findViewById(R.id.chipRestPostres)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val txtMenuRest = view.findViewById<TextView>(R.id.txtListadoMenuRest)

        val ft = parentFragmentManager.beginTransaction()
        ft.replace(R.id.frameMenuRestaurant, RestTodoFragment())
        ft.commit()

        chipTodo.setOnClickListener {
            txtMenuRest.text = "Todo el menu disponible"
            parentFragmentManager.beginTransaction()
                .replace(R.id.frameMenuRestaurant, RestTodoFragment())
                .commit()
        }

        chipEspecialidad.setOnClickListener {
            txtMenuRest.text = "Especialidad del Restaurante"
            val ft = parentFragmentManager.beginTransaction()
            ft.replace(R.id.frameMenuRestaurant, RestEspecialidadFragment())
            ft.commit()
        }

        chipPlatos.setOnClickListener {
            txtMenuRest.text = "Menu de Platos"
            val ft = parentFragmentManager.beginTransaction()
            ft.replace(R.id.frameMenuRestaurant, RestPlatosFragment())
            ft.commit()
        }

        chipSnacks.setOnClickListener {
            txtMenuRest.text = "Menu de Snacks"
            val ft = parentFragmentManager.beginTransaction()
            ft.replace(R.id.frameMenuRestaurant, RestSnacksFragment())
            ft.commit()
        }

        chipBebidas.setOnClickListener {
            txtMenuRest.text = "Menu de Bebidas"
            val ft = parentFragmentManager.beginTransaction()
            ft.replace(R.id.frameMenuRestaurant, RestBebidasFragment())
            ft.commit()
        }

        chipPostres.setOnClickListener {
            txtMenuRest.text = "Menu de Postres"
            val ft = parentFragmentManager.beginTransaction()
            ft.replace(R.id.frameMenuRestaurant, RestPostresFragment())
            ft.commit()
        }
    }
}