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
import com.tp3.tp3_thebookbox.fragments.AddBookFragmentDirections
import com.tp3.tp3_thebookbox.fragments.CatalogueFragmentDirections
import kotlinx.coroutines.delay
import kotlinx.coroutines.tasks.await
import java.io.File
import java.sql.Date
import kotlin.collections.contains as contains1

class CatalogueViewModel : ViewModel() {

    lateinit var adapter : CatalogueAdapter
    val db = Firebase.firestore

    var bookList : MutableList<Book> = mutableListOf()
    var id : Int = 0

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
    fun publishBook(book: Book, binding: FragmentAddBookBinding){
        db.collection("books")
            .document(book.id)
            .set(book)
            .addOnSuccessListener {
                Log.d(TAG, "DOCUMENTO AGREGADO CORRECTAMENTE")
                val action = AddBookFragmentDirections.actionAddBookFragmentToCatalogueFragment()
                binding.root.findNavController().navigate(action)
            }
            .addOnFailureListener { e -> Log.w(TAG, "ERROR AL CARGAR EL DOCUMENTO", e) }
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
    fun getLastId(){
        db.collection("books")
            .get()
            .addOnSuccessListener {
                id = it.documents.size
                println("LAST ID " + it.documents.size)
                Log.d(TAG, "LIBROS ENCONTRADOS: " + it.documents.toString())
            }
            .addOnFailureListener { e -> Log.w(TAG, "ERROR AL BUSCAR LOS LIBROS", e) }
    }
}