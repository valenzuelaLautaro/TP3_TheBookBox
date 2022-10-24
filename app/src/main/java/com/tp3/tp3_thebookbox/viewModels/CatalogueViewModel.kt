package com.tp3.tp3_thebookbox.viewModels

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.net.Uri
import android.util.Log
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
import java.io.File
import java.sql.Date

class CatalogueViewModel : ViewModel() {

    lateinit var adapter : CatalogueAdapter
    val db = Firebase.firestore
    val storage = Firebase.storage
    val storageRef = storage.reference

    var bookList : MutableList<Book> = mutableListOf()

    val user = User("Lautaro Valenzuela", "lautarovalenzuela94@gmail.com", "callefalsa123", "https://i.pinimg.com/736x/b9/fd/20/b9fd20744ad6f008787ffed46a0b7149--s-cartoons-bart-simpson.jpg", Date(27/12/2001), "1166517457")

    var book1 = Book(0,"La iliada", "Homero", Date(27/12/2001), "Guerra", "Ninguna", "https://global-uploads.webflow.com/6034d7d1f3e0f52c50b2adee/62542902e650b33bf6b7a912_60e710db1c3ed16b7bfff0df_9788418008962_Cub.jpeg", user)
    var book2 = Book(1,"La vuelta al mundo", "Julio Verne", Date(18/12/2000), "Aventura", "Viajeros", "https://www.tematika.com/media/catalog/Ilhsa/Imagenes/673611.jpg", user)
    var book3 = Book(2,"Martin Fierro", "Jose Hernandez", Date(19/6/2003), "Aventura", "Gauchos Anonimos", "https://imgserver1.fierro.com.ar/get/thumb/188/272/HERCI-QUE9879246276=9789879246276.jpg?randomize=1", user)

    fun addBooks(){
        bookList.add(book1)
        bookList.add(book2)
        bookList.add(book3)
    }

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
            if(inputEditorial.text.toString().isEmpty()){
                isValid = false
                inputEditorial.error = "Campo requerido."
            }
            //urlImage
            if(inputUrlImage.text.toString().isEmpty()){
                isValid = false
                inputUrlImage.error = "Campo requerido."
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
    fun uploadPhoto(){
        var file = Uri.fromFile(File("path/to/images/portada.jpg"))
        val portadaRef = storageRef.child("Portadas/${file.lastPathSegment}")
        val uploadTask = portadaRef.putFile(file)

        val urlTask = uploadTask.continueWithTask { task ->
            if (!task.isSuccessful) {
                task.exception?.let {
                    throw it
                }
            }
            portadaRef.downloadUrl
        }.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val downloadUri = task.result
            } else {
                // Handle failures
                // ...
            }
        }
    }

    fun getAllBooks(){
        db.collection("books")
            .get()
            .addOnSuccessListener { resultado ->
                for(document in resultado) {
                    bookList.add(document.toObject<Book>())
                }
            }
            .addOnFailureListener { e -> Log.d(TAG, "Error getting documents: ", e) }
        println("LISTA DE LIBROS: " + bookList)
    }
}