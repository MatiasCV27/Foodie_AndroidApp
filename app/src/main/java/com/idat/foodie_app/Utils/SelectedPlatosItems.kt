package com.idat.foodie_app.Utils

import com.idat.foodie_app.Modelos.CarritoItems

object SelectedPlatosItems {

    //var nombre: String = ""
    //var precio: Double = 0.0
    //var imagen: String = ""

    val items = ArrayList<CarritoItems>()

    var precioTotal: Double = 0.0

    fun addItem(nombre: String, precio: Double, imagen: String) {
        val newItem = CarritoItems(nombre, precio, imagen)
        items.add(newItem)
    }

    fun clearItems() {
        items.clear()
    }


}