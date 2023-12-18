package com.idat.foodie_app.Adaptadores

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.idat.foodie_app.Modelos.HistorialItems
import com.idat.foodie_app.R

class AdapterHistorialItems (private var items: ArrayList<HistorialItems>):
    RecyclerView.Adapter<AdapterHistorialItems.ViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterHistorialItems.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.plantillla_historial, parent, false)
        return AdapterHistorialItems.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdapterHistorialItems.ViewHolder, position: Int) {
        val item = items[position]

        val nombresList = item.nombres.split(", ")
        val nombresFormatted = nombresList.joinToString("\n")
        val preciosList = item.precios.split(", ")
        val preciosFormatted = preciosList.joinToString("\n")

        holder.nombresHis.text = nombresFormatted
        holder.preciosHis.text = preciosFormatted
        holder.fechaHis.text = item.fecha
        holder.precioTotalHis.text = "S/ " + item.precioTotal
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(view : View): RecyclerView.ViewHolder(view) {
        val fechaHis: TextView = view.findViewById(R.id.txtFechaHistorial)
        val nombresHis: TextView = view.findViewById(R.id.txtNombresHistorial)
        val preciosHis: TextView = view.findViewById(R.id.txtPreciosHistorial)
        val precioTotalHis: TextView = view.findViewById(R.id.txtPTHistorial)
    }
}