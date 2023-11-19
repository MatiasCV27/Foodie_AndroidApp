package com.idat.foodie_app.NavbarFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.chip.Chip
import com.idat.foodie_app.MenuCatRestFragment.*
import com.idat.foodie_app.MenuRestauranteFragment.*
import com.idat.foodie_app.R

class HomeFragment : Fragment() {

    private lateinit var chipCatTodo: Chip
    private lateinit var chipCatPollos: Chip
    private lateinit var chipCatMariscos: Chip
    private lateinit var chipCatChifa: Chip
    private lateinit var chipCatParrillas: Chip
    private lateinit var chipCatTradicional: Chip

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)

        chipCatTodo = view.findViewById(R.id.chipCatTodo)
        chipCatPollos = view.findViewById(R.id.chipCatPollos)
        chipCatMariscos = view.findViewById(R.id.chipCatMariscos)
        chipCatChifa = view.findViewById(R.id.chipCatChifa)
        chipCatParrillas = view.findViewById(R.id.chipCatParrilla)
        chipCatTradicional = view.findViewById(R.id.chipCatTradicional)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val txtCatRest = view.findViewById<TextView>(R.id.txtListadoCatRest)

        val ft = parentFragmentManager.beginTransaction()
        ft.replace(R.id.frameMenuHome, CatRestTodoFragment())
        ft.commit()

        chipCatTodo.setOnClickListener {
            txtCatRest.text = "Todos los restaurantes disponibles"
            parentFragmentManager.beginTransaction()
                .replace(R.id.frameMenuHome, CatRestTodoFragment())
                .commit()
        }

        chipCatPollos.setOnClickListener {
            txtCatRest.text = "Restaurantes de Pollos"
            val ft = parentFragmentManager.beginTransaction()
            ft.replace(R.id.frameMenuHome, CatRestPollosFragment())
            ft.commit()
        }

        chipCatMariscos.setOnClickListener {
            txtCatRest.text = "Restaurantes de Mariscos"
            val ft = parentFragmentManager.beginTransaction()
            ft.replace(R.id.frameMenuHome, CatRestMariscosFragment())
            ft.commit()
        }

        chipCatChifa.setOnClickListener {
            txtCatRest.text = "Restaurantes de Chifas"
            val ft = parentFragmentManager.beginTransaction()
            ft.replace(R.id.frameMenuHome, CatRestChifaFragment())
            ft.commit()
        }

        chipCatParrillas.setOnClickListener {
            txtCatRest.text = "Restaurantes de Parrillas"
            val ft = parentFragmentManager.beginTransaction()
            ft.replace(R.id.frameMenuHome, CatRestParrillasFragment())
            ft.commit()
        }

        chipCatTradicional.setOnClickListener {
            txtCatRest.text = "Restaurantes Tradicionales"
            val ft = parentFragmentManager.beginTransaction()
            ft.replace(R.id.frameMenuHome, CatRestTradicionalFragment())
            ft.commit()
        }
    }
}