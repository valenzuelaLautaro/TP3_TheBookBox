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

        // Genero mi lista de libros
    var book1 = Book(0,"La iliada", "Homero", Date(27/12/2001), "Guerra", "Ninguna", "https://global-uploads.webflow.com/6034d7d1f3e0f52c50b2adee/62542902e650b33bf6b7a912_60e710db1c3ed16b7bfff0df_9788418008962_Cub.jpeg", user.email)
    var book2 = Book(1,"La vuelta al mundo", "Julio Verne", Date(18/12/2000), "Aventura", "Viajeros", "https://www.tematika.com/media/catalog/Ilhsa/Imagenes/673611.jpg", user.email)
    var book3 = Book(2,"Martin Fierro", "Jose Hernandez", Date(19/6/2003), "Aventura", "Gauchos Anonimos", "https://imgserver1.fierro.com.ar/get/thumb/188/272/HERCI-QUE9879246276=9789879246276.jpg?randomize=1", user.email)

    fun addBooks(){
        bookList.add(book1)
        bookList.add(book2)
        bookList.add(book3)
    }

    fun addFavorites( book: Book){

        // android no soporta enlistar subcolecciones


    }

    fun getFavBooks(binding: FragmentFavouriteBooksBinding){
        db.collection("books")
            .whereEqualTo("idUser", user.email) // condicion de busqueda
            .get()
            .addOnSuccessListener { resultado ->
                bookList.clear()
                for(document in resultado) {

                    bookList.add(document.toObject<Book>())
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

}


