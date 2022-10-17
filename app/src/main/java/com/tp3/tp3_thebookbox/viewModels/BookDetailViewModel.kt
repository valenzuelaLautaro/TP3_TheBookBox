package com.tp3.tp3_thebookbox.viewModels

import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.tp3.tp3_thebookbox.databinding.FragmentBookDetailBinding
import com.tp3.tp3_thebookbox.entities.Book

class BookDetailViewModel : ViewModel() {

    fun setBookDetail(book: Book, binding: FragmentBookDetailBinding){
        Glide.with(binding.root).load(book.urlImage).centerCrop().into(binding.bookDetailImage)
        binding.bookDetailTitle.text = book.nombre
        binding.bookDetailAutor.text = book.autor
        binding.bookDetailEditorial.text = book.editorial
        binding.bookDetailYear.text = book.edicion.toString()
    }
}