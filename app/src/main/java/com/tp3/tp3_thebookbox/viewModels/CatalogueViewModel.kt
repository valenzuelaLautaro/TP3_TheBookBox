package com.tp3.tp3_thebookbox.viewModels

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.net.Uri
import android.util.Log
import android.widget.Adapter
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.bumptech.glide.Glide.init
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.tp3.tp3_thebookbox.R
import com.tp3.tp3_thebookbox.adapters.CatalogueAdapter
import com.tp3.tp3_thebookbox.databinding.FragmentAddBookBinding
import com.tp3.tp3_thebookbox.databinding.FragmentCatalogueBinding
import com.tp3.tp3_thebookbox.entities.Book
import com.tp3.tp3_thebookbox.entities.User
import com.tp3.tp3_thebookbox.fragments.CatalogueFragmentDirections
import kotlinx.coroutines.delay
import kotlinx.coroutines.tasks.await
import java.io.File
import java.sql.Date
import kotlin.collections.contains as contains1

class CatalogueViewModel : ViewModel() {

    lateinit var adapter : CatalogueAdapter
    val db = Firebase.firestore
    val storage = Firebase.storage
    val storageRef = storage.reference
    var bookList : MutableList<Book> = mutableListOf()

    val user = User("Lautaro Valenzuela", "lautarovalenzuela94@gmail.com", "callefalsa123", "https://i.pinimg.com/736x/b9/fd/20/b9fd20744ad6f008787ffed46a0b7149--s-cartoons-bart-simpson.jpg", Date(27/12/2001), "1166517457")


    fun onItemSelected(book: Book, binding: FragmentCatalogueBinding){
        val action = CatalogueFragmentDirections.actionCatalogueFragmentToBookDetailFragment(book)
        binding.root.findNavController().navigate(action)
    }
    fun addBook(binding: FragmentCatalogueBinding){
        val action = CatalogueFragmentDirections.actionCatalogueFragmentToAddBookFragment()
        binding.root.findNavController().navigate(action)
    }
    fun validateForm(binding: FragmentAddBookBinding) : Boolean {
        var isValid = true

        with(binding){
            //nombre
            if(inputTitle.text.toString().isEmpty()){
                isValid = false
                inputTitle.error = "Campo requerido."
            }
            //autor
            if(inputAutor.text.toString().isEmpty()){
                isValid = false
                inputAutor.error = "Campo requerido."
            }
            //genero
            if(inputGenero.text.toString().isEmpty()){
                isValid = false
                inputGenero.error = "Campo requerido."
            }
            //editorial
            if(inputEditorial.text.toString().isEmpty()) {
                isValid = false
                inputEditorial.error = "Campo requerido."
            }

        }

        return isValid
    }
    fun publishBook(book: Book){
        db.collection("books").document()
            .set(book)
            .addOnSuccessListener { Log.d(TAG, "DOCUMENTO AGREGADO CORRECTAMENTE") }
            .addOnFailureListener { e -> Log.w(TAG, "ERROR AL CARGAR EL DOCUMENTO", e) }
    }

    fun uploadBook(){

    }
    suspend fun uploadPhoto(path:Uri) : String {
        var downloadUri : String = ""

        val portadaRef = storageRef.child("portadas/${path.lastPathSegment}")
        val uploadTask = portadaRef.putFile(path)
        Log.d("llego hasta aca", "1")
        uploadTask.continueWithTask { task ->
            if (!task.isSuccessful) {
                task.exception?.let {
                    Log.d("llego hasta aca", "error")
                    throw it

                }
            }
            Log.d("llego hasta aca", "2")
            portadaRef.downloadUrl
        }.addOnCompleteListener { task ->
            Log.d("llego hasta aca", "3")
            if (task.isSuccessful) {
                downloadUri = task.result.toString()
                Log.d("URI FIREBASE", "LA URI ES: $downloadUri")
            } else {
                // Handle failures
                // ...
                Log.d("error", "Error al enviar foto a Firebase")
            }
        }.await()
        
        Log.d("resultado", downloadUri)
        return downloadUri
    }

    fun getAllBooks(binding: FragmentCatalogueBinding){
        db.collection("books")
            .get()
            .addOnSuccessListener { resultado ->
                bookList.clear()
                for(document in resultado) {
                    bookList.add(document.toObject<Book>())
                }
                Log.d("test",bookList.toString())
                adapter = CatalogueAdapter(
                    bookList,
                    { book -> onItemSelected(book, binding) })
                binding.recyclerCatalogo.adapter = adapter
            }
            .addOnFailureListener { e -> Log.d(TAG, "Error getting documents: ", e) }
        println("LISTA DE LIBROS: " + bookList)
    }
}