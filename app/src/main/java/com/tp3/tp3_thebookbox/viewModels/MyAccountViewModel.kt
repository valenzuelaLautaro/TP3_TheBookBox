package com.tp3.tp3_thebookbox.viewModels

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.tp3.tp3_thebookbox.databinding.FragmentMyAccountBinding
import com.tp3.tp3_thebookbox.entities.User
import com.tp3.tp3_thebookbox.fragments.MyAccountFragment
import com.tp3.tp3_thebookbox.fragments.MyAccountFragmentDirections

class MyAccountViewModel : ViewModel() {

    val db = Firebase.firestore
    var user : User? = null

    fun getAccount(email: String, binding: FragmentMyAccountBinding){
        db.collection("users")
            .document(email)
            .get()
            .addOnSuccessListener { resultado ->
                user = resultado.toObject<User>()
                Log.d(TAG, "USUARIO ENCONTRADO " + user.toString()) }
            .addOnFailureListener { e -> Log.w(TAG, "USUARIO NO ENCONTRADO" + e) }

        setData(binding)
    }

    private fun setData(binding: FragmentMyAccountBinding) {
        binding.userName.text = user?.nombre
        binding.userMail.text = user?.email
        binding.userPass.text = user?.password
        binding.userBirth.text = user?.fechaNacimiento.toString()
        binding.userPhone.text = user?.telefono
    }

    fun navigateToBookList (binding: FragmentMyAccountBinding){
        var action = MyAccountFragmentDirections.actionMyAccountFragmentToMyBooksFragment()
        binding.root.findNavController().navigate(action)
    }
    fun logout (binding: FragmentMyAccountBinding){
        var action = MyAccountFragmentDirections.actionMyAccountFragmentToLogInFragment2()
        binding.root.findNavController().navigate(action)
    }
}