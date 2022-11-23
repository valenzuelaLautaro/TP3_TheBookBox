package com.tp3.tp3_thebookbox.viewModels

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.tp3.tp3_thebookbox.adapters.CatalogueAdapter
import com.tp3.tp3_thebookbox.adapters.MyBooksAdapter
import com.tp3.tp3_thebookbox.databinding.FragmentCatalogueBinding
import com.tp3.tp3_thebookbox.databinding.FragmentMyBooksBinding
import com.tp3.tp3_thebookbox.entities.Book
import com.tp3.tp3_thebookbox.entities.User
import com.tp3.tp3_thebookbox.fragments.MyBooksFragment
import com.tp3.tp3_thebookbox.fragments.MyBooksFragmentDirections
import java.sql.Date

class MyBooksViewModel : ViewModel() {

    lateinit var adapter : MyBooksAdapter
    var bookList : MutableList<Book> = mutableListOf()
    val db = Firebase.firestore

    // creo mi usuario
    private val email : String = "martin.blasson@gmail.com"

    fun getMyBooks(binding: FragmentMyBooksBinding){
        db.collection("books")
            .whereEqualTo("idUser", email)
            .get()
            .addOnSuccessListener { resultado ->
                bookList.clear()
                for(document in resultado) {
                    bookList.add(document.toObject<Book>())
                }
                Log.d("libros recuperados",bookList.toString())
                adapter = MyBooksAdapter(
                    bookList,
                    { book -> onItemSelected(book, binding) })
                binding.recyclerMyBooks.adapter = adapter
            }
            .addOnFailureListener { e -> Log.d(ContentValues.TAG, "Error getting documents: ", e) }
        println("LISTA DE LIBROS: " + bookList)
    }

    fun onItemSelected(book: Book, binding: FragmentMyBooksBinding){
        var action = MyBooksFragmentDirections.actionMyBooksFragmentToMyBookInformationFragment(book)
        binding.root.findNavController().navigate(action)
    }

}