package com.tp3.tp3_thebookbox.viewModels

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.tp3.tp3_thebookbox.databinding.FragmentAddBookBinding
import com.tp3.tp3_thebookbox.databinding.FragmentBookDetailBinding
import com.tp3.tp3_thebookbox.entities.Book
import com.tp3.tp3_thebookbox.fragments.AddBookFragmentDirections
import com.tp3.tp3_thebookbox.fragments.BookDetailFragmentDirections
import com.tp3.tp3_thebookbox.fragments.MyAccountFragmentDirections

class BookDetailViewModel : ViewModel() {

    val db = Firebase.firestore

    fun setBookDetail(book: Book, binding: FragmentBookDetailBinding){
        Glide.with(binding.root).load(book.urlImage).centerCrop().into(binding.bookDetailImage)
        binding.bookDetailTitle.text = book.nombre
        binding.bookDetailAutor.text = book.autor
        binding.bookDetailEditorial.text = book.editorial
        binding.bookDetailYear.text = book.edicion.toString()
    }

    fun addFavouriteBook(book: Book, email: String,binding: FragmentBookDetailBinding){
        db.collection("books")
            .document(book.id)
            .update("usersFav", FieldValue.arrayUnion(email))
            .addOnSuccessListener {
                Log.d(TAG, "Se ha añadido correctamente a favoritos.")
                val action = BookDetailFragmentDirections.actionBookDetailFragmentToFavouriteBooksFragment()
                binding.root.findNavController().navigate(action)
            }
            .addOnFailureListener { e -> Log.w(TAG, "No se pudo añadir a favoritos.", e) }
    }

    fun deleteBook(book: Book){
        db.collection("books").document(book.id.toString())
            .delete()
            .addOnSuccessListener { Log.d(TAG, "El libro " + book.nombre + " fue borrado exitosamente.") }
            .addOnFailureListener { e -> Log.w(TAG, "Ha ocurrido un error al borrar el libro.", e) }
    }

    fun navigateToComments(book: Book, binding: FragmentBookDetailBinding) {
        var action = BookDetailFragmentDirections.actionBookDetailFragmentToCommentFragment(book)
        binding.root.findNavController().navigate(action)
    }

    fun navigateToCatalogue(binding: FragmentBookDetailBinding) {
        var action = BookDetailFragmentDirections.actionBookDetailFragmentToCatalogueFragment()
        binding.root.findNavController().navigate(action)
    }
}