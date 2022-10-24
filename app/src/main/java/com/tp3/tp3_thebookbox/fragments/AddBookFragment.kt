package com.tp3.tp3_thebookbox.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.viewbinding.ViewBinding
import com.tp3.tp3_thebookbox.R
import com.tp3.tp3_thebookbox.databinding.FragmentAddBookBinding
import com.tp3.tp3_thebookbox.entities.Book
import com.tp3.tp3_thebookbox.viewModels.AddBookViewModel
import com.tp3.tp3_thebookbox.viewModels.CatalogueViewModel
import java.sql.*

class AddBookFragment : Fragment() {

    private lateinit var binding: FragmentAddBookBinding
    private val viewModel: CatalogueViewModel by viewModels()
    private var idBook: Int = 5

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBookBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        binding.publishBookButton.setOnClickListener {


            val book = Book(idBook, binding.inputTitle.text.toString(),
                binding.inputAutor.text.toString(),
                Date(binding.inputEdicion.text.toString().toInt().toLong()),
                binding.inputGenero.text.toString(),
                binding.inputEditorial.text.toString(),
                binding.inputUrlImage.text.toString(),
                null)


            if(viewModel.validateForm(binding)){
                viewModel.publishBook(book)
                viewModel.uploadBook()
                viewModel.uploadPhoto()
                println("TODO OK")
            }
            idBook++
        }
    }
}