package com.tp3.tp3_thebookbox.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Comment (var texto: String,
               var cantEstrellas: String,
               var publicador: User) : Parcelable {

}