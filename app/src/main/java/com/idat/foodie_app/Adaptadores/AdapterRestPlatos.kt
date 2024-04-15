package com.idat.foodie_app.Adaptadores

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.idat.foodie_app.Modelos.RestPlatos
import com.idat.foodie_app.R
import com.idat.foodie_app.Utils.SelectedPlatosItems
import com.idat.foodie_app.Utils.SelectedUserStats
import com.squareup.picasso.Picasso

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
        Picasso.get().load(item.imagen).into(holder.imagenV)
        holder.irCarrito.setOnClickListener {

            SelectedPlatosItems.addItem(item.nombre, item.precio, item.imagen)
            SelectedPlatosItems.precioTotal += item.precio
            SelectedUserStats.nombres += item.nombre + ", "
            SelectedUserStats.precios += item.precio.toString() + ", "
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
    class ViewHolder(view : View): RecyclerView.ViewHolder(view) {
        val nombreV: TextView = view.findViewById(R.id.cardPlatoNombre)
        val preciov: TextView = view.findViewById(R.id.cardPlatoPrecio)
        val imagenV: ImageView = view.findViewById(R.id.cardPlatoImagen)
        val irCarrito: CardView = view.findViewById(R.id.cardPlatos)

    }

}