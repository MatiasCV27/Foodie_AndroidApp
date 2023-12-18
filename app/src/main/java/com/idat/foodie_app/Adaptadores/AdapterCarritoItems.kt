package com.idat.foodie_app.Adaptadores

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.idat.foodie_app.Modelos.CarritoItems
import com.idat.foodie_app.R
import com.squareup.picasso.Picasso

class AdapterCarritoItems (private var items: ArrayList<CarritoItems>):
    RecyclerView.Adapter<AdapterCarritoItems.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterCarritoItems.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.prueba_carrito, parent, false)
        return AdapterCarritoItems.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdapterCarritoItems.ViewHolder, position: Int) {
        val item = items[position]
        holder.nombreCar.text = item.nombre
        holder.precioCar.text = item.precio.toString()
        Picasso.get().load(item.imagen).into(holder.imagenCar)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(view : View): RecyclerView.ViewHolder(view) {
        val nombreCar: TextView = view.findViewById(R.id.cardCarNombre)
        val precioCar: TextView = view.findViewById(R.id.cardCarPrecio)
        val imagenCar: ImageView = view.findViewById(R.id.cardCarImagen)
    }

    fun addItem(item: CarritoItems) {
        items.add(item)
        notifyItemInserted(items.size - 1)
    }

}
