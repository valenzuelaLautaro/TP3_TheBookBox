package com.tp3.tp3_thebookbox.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.tp3.tp3_thebookbox.databinding.FragmentAddBookBinding
import com.tp3.tp3_thebookbox.entities.Book
import com.tp3.tp3_thebookbox.entities.User
import com.tp3.tp3_thebookbox.viewModels.CatalogueViewModel
import java.util.*

class AddBookFragment : Fragment() {

    private lateinit var binding: FragmentAddBookBinding
    private val viewModel: CatalogueViewModel by activityViewModels()
    private var idBook: Int = 5
    private val user = User("lautaro", "lautarovalenzuela94@gmail.com", "callefalsa123", "www.nada.png", Date(12/10/2002), "1166517457")

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
                user.email)

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