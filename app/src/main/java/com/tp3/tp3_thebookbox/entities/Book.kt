package com.tp3.tp3_thebookbox.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
class Book (var id: Int = -1,
            var nombre: String = "",
            var autor: String = "",
            var edicion: Date? = null,
            var genero: String = "",
            var editorial: String = "",
            var urlImage: String = "",
            var publicador: User? = null) : Parcelable{

}