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
import com.tp3.tp3_thebookbox.adapters.FavsAdapter
import com.tp3.tp3_thebookbox.databinding.FragmentCatalogueBinding
import com.tp3.tp3_thebookbox.databinding.FragmentFavouriteBooksBinding
import com.tp3.tp3_thebookbox.databinding.FragmentMyBooksBinding
import com.tp3.tp3_thebookbox.entities.Book
import com.tp3.tp3_thebookbox.entities.User
import com.tp3.tp3_thebookbox.fragments.FavouriteBooksFragment
import com.tp3.tp3_thebookbox.fragments.FavouriteBooksFragmentDirections
import com.tp3.tp3_thebookbox.fragments.MyBooksFragmentDirections
import java.sql.Date

    class FavouriteBooksViewModel : ViewModel() {
    lateinit var adapter : FavsAdapter
    val db = Firebase.firestore
    var bookList : MutableList<Book> = mutableListOf()

    // creo mi usuario
    val user = User("Lautaro Valenzuela", "lautarovalenzuela94@gmail.com", "callefalsa123", "https://i.pinimg.com/736x/b9/fd/20/b9fd20744ad6f008787ffed46a0b7149--s-cartoons-bart-simpson.jpg", Date(27/12/2001), "1166517457")

    fun getFavBooks(email: String, binding: FragmentFavouriteBooksBinding){
        var book : Book

        db.collection("books")
            .get()
            .addOnSuccessListener { resultado ->
                bookList.clear()
                for(document in resultado) {
                    book = document.toObject<Book>()
                    println("OBJETO: " + book.toString())
                    println("BOOLEAN: " + book.usersFav.contains(email))
                    if(book.usersFav.contains(email)){
                        bookList.add(book)
                    }
                }
                Log.d("test",bookList.toString())
                adapter = FavsAdapter(
                    bookList,
                    { book -> onItemSelected(book, binding) })
                binding.recyclerCatalogo.adapter = adapter
            }
            .addOnFailureListener { e -> Log.d(ContentValues.TAG, "Error getting documents: ", e) }
        println("LISTA DE LIBROS: " + bookList)
    }

    fun onItemSelected(book: Book, binding: FragmentFavouriteBooksBinding){
        var action = FavouriteBooksFragmentDirections.actionFavouriteBooksFragmentToBookDetailFragment(book)
        binding.root.findNavController().navigate(action)
    }
    fun filterByName(name: String) {
        bookList.filter { book ->
            book.nombre.contains(name)
        }
    }

}


