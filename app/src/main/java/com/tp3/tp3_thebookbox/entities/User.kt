package com.tp3.tp3_thebookbox.entities

import java.sql.Date

class User (var id: Int,
            var nombre: String,
            var email: String,
            var password: String,
            var urlImage: String,
            var fechaNacimiento: Date,
            var telefono: String,
            var librosFavoritos: MutableList<Book>,
            var misLibros: MutableList<Book>) {

}