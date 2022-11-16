package com.tp3.tp3_thebookbox.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Comment (var idComment: String = "",
               var text: String = "",
               var stars: Int = 0,
               var idUser: String = "",
               var idBook: String = "") : Parcelable {

}