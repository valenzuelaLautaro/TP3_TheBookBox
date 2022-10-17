package com.tp3.tp3_thebookbox.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.sql.Date

@Parcelize
class Book (var id: Int,
            var nombre: String,
            var autor: String,
            var edicion: Date,
            var genero: String,
            var editorial: String,
            var urlImage: String,
            var publicador: User) : Parcelable{

}