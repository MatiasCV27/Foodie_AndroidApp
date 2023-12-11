package com.idat.foodie_app.Modelos

data class RestPlatos(

    var idPlato: String = "",
    var idRest: String = "",
    var nombre: String = "",
    var precio: Double = 0.0,
    var cantidad: Int = 0,
    var categoria: String = "",
    var imagen: String = ""

)