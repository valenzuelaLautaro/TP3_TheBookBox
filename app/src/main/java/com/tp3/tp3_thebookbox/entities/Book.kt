package com.tp3.tp3_thebookbox.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class Book (var id: String = "",
            var nombre: String = "",
            var autor: String = "",
            var edicion: Date? = null,
            var genero: String = "",
            var editorial: String = "",
            var urlImage: String = "",
            var idUser: String = "",
            var usersFav: MutableList<String> = mutableListOf()) : Parcelable{


}