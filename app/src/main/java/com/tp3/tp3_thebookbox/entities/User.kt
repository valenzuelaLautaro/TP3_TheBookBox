package com.tp3.tp3_thebookbox.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
class User (var nombre: String = "",
            var email: String = "",
            var password: String = "",
            var urlImage: String = "",
            var fechaNacimiento: Date? = null,
            var telefono: String = "") : Parcelable {

}