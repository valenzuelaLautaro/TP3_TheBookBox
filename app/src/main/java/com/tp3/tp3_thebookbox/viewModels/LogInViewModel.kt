package com.tp3.tp3_thebookbox.viewModels

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.tp3.tp3_thebookbox.databinding.FragmentLogInBinding
import com.tp3.tp3_thebookbox.entities.Book
import com.tp3.tp3_thebookbox.entities.User

class LogInViewModel : ViewModel() {

    var db = Firebase.firestore

    fun getUser(email: String) : User? {
        var user : User? = null

        db.collection("users")
            .get()
            .addOnSuccessListener { resultado ->
                for(document in resultado){
                    if(document.toObject<User>().email.equals(email)){
                        user = document.toObject<User>()
                    }
                }
                Log.d(TAG,"USUARIO ENCONTRADO EXITOSAMENTE: " + user.toString())
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "USUARIO NO ENCONTRADO.", e)
            }
        return user
    }

    fun logIn(binding: FragmentLogInBinding){
        binding.emailInput.text.toString()
        binding.passwordInput.text.toString()
    }

}