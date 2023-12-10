package com.idat.foodie_app.MenuRestauranteFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.idat.foodie_app.Adaptadores.AdapterRestaurantes
import com.idat.foodie_app.Modelos.Restaurantes
import com.idat.foodie_app.R
import com.idat.foodie_app.databinding.FragmentRestTodoBinding

class RestTodoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_rest_todo, container, false)
    }

}