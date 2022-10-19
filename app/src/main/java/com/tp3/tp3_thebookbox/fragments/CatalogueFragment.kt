package com.tp3.tp3_thebookbox.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tp3.tp3_thebookbox.R
import com.tp3.tp3_thebookbox.adapters.CatalogueAdapter
import com.tp3.tp3_thebookbox.databinding.FragmentCatalogueBinding
import com.tp3.tp3_thebookbox.entities.Book
import com.tp3.tp3_thebookbox.entities.User
import com.tp3.tp3_thebookbox.viewModels.CatalogueViewModel
import java.sql.Date

class CatalogueFragment : Fragment() {

    private lateinit var binding: FragmentCatalogueBinding
    private val viewModel: CatalogueViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCatalogueBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        viewModel.addBooks()

        binding.recyclerCatalogo.setHasFixedSize(true)
        binding.recyclerCatalogo.layoutManager = LinearLayoutManager(context)

        viewModel.adapter = CatalogueAdapter(
            viewModel.bookList,
            { book -> viewModel.onItemSelected(book, binding) })
        binding.recyclerCatalogo.adapter = viewModel.adapter

        //metodo para agregar un nuevo libro al catalogo : subir una publicacion nueva
        binding.addBookButton.setOnClickListener {
            viewModel.addBook(binding)
        }
    }

}