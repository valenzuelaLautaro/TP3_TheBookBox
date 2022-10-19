package com.tp3.tp3_thebookbox.viewModels

import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.tp3.tp3_thebookbox.adapters.CatalogueAdapter
import com.tp3.tp3_thebookbox.databinding.FragmentCatalogueBinding
import com.tp3.tp3_thebookbox.entities.Book
import com.tp3.tp3_thebookbox.entities.User
import com.tp3.tp3_thebookbox.fragments.CatalogueFragmentDirections
import java.sql.Date

class CatalogueViewModel : ViewModel() {

    val db = Firebase.firestore
    lateinit var adapter : CatalogueAdapter

    var bookList : MutableList<Book> = mutableListOf()

    val user = User(0, "Lautaro Valenzuela", "lautarovalenzuela94@gmail.com", "callefalsa123", "https://i.pinimg.com/736x/b9/fd/20/b9fd20744ad6f008787ffed46a0b7149--s-cartoons-bart-simpson.jpg", Date(27/12/2001), "1166517457", bookList, bookList)

    var book1 = Book(0, "La iliada", "Homero", Date(27/12/2001), "Guerra", "Ninguna", "https://global-uploads.webflow.com/6034d7d1f3e0f52c50b2adee/62542902e650b33bf6b7a912_60e710db1c3ed16b7bfff0df_9788418008962_Cub.jpeg", user)
    var book2 = Book(1, "La vuelta al mundo", "Julio Verne", Date(18/12/2000), "Novela", "Viajeros", "https://www.tematika.com/media/catalog/Ilhsa/Imagenes/673611.jpg", user)
    var book3 = Book(2, "Martin Fierro", "Jose Hernandez", Date(19/6/2003), "Poesia", "Gauchos Anonimos", "https://imgserver1.fierro.com.ar/get/thumb/188/272/HERCI-QUE9879246276=9789879246276.jpg?randomize=1", user)

    fun addBooks(){
        bookList.add(book1)
        bookList.add(book2)
        bookList.add(book3)
    }

    //aca me quede, debo copiar esta funcion con la navegacion del fragment y que el fragment se lo pase al
    //adapter cuando se instancia
    fun onItemSelected(book: Book, binding: FragmentCatalogueBinding){
        val action = CatalogueFragmentDirections.actionCatalogueFragmentToBookDetailFragment(book)
        binding.root.findNavController().navigate(action)
    }
    fun addBook(binding: FragmentCatalogueBinding){
        val action = CatalogueFragmentDirections.actionCatalogueFragmentToAddBookFragment()
        binding.root.findNavController().navigate(action)
    }
}