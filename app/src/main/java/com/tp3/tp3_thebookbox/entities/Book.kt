package com.tp3.tp3_thebookbox.entities

import java.sql.Date

class Book (var id: Int,
            var nombre: String,
            var autor: String,
            var edicion: Date,
            var genero: String,
            var editorial: String,
            var urlImage: String) {

}