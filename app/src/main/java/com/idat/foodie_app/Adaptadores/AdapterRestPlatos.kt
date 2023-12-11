package com.idat.foodie_app.Adaptadores

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.idat.foodie_app.Modelos.RestPlatos
import com.idat.foodie_app.R

class AdapterRestPlatos (private var items: ArrayList<RestPlatos>):
    RecyclerView.Adapter<AdapterRestPlatos.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterRestPlatos.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.prueba_platos, parent, false)
        return AdapterRestPlatos.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdapterRestPlatos.ViewHolder, position: Int) {
        val item = items[position]
        holder.nombreV.text = item.nombre
        holder.preciov.text = item.precio.toString()
        holder.imagenV.text = item.imagen
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(view : View): RecyclerView.ViewHolder(view) {
        val nombreV: TextView = view.findViewById(R.id.cardPlatoNombre)
        val preciov: TextView = view.findViewById(R.id.cardPlatoPrecio)
        val imagenV: TextView = view.findViewById(R.id.cardPlatoImagen)
    }
}