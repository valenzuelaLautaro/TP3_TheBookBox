package com.tp3.tp3_thebookbox.viewModels

import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.tp3.tp3_thebookbox.databinding.FragmentMyBookInformationBinding
import com.tp3.tp3_thebookbox.entities.Book

class MyBookInformationViewModel : ViewModel() {

    fun setBookInfo(book: Book, binding: FragmentMyBookInformationBinding){
        Glide.with(binding.root).load(book.urlImage).centerCrop().into(binding.myBookphoto)
        binding.myBookName.text = book.nombre
        binding.myBookAuthor.text = book.autor
        binding.myBookEditorial.text = book.editorial
        binding.myBookYear.text = book.edicion.toString()
    }
}