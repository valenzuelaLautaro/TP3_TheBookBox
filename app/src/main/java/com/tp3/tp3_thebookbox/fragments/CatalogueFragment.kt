package com.tp3.tp3_thebookbox.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.tp3.tp3_thebookbox.adapters.CatalogueAdapter
import com.tp3.tp3_thebookbox.databinding.FragmentCatalogueBinding
import com.tp3.tp3_thebookbox.viewModels.CatalogueViewModel

class CatalogueFragment : Fragment() {

    private lateinit var binding: FragmentCatalogueBinding
    private val viewModel: CatalogueViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCatalogueBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        viewModel.getAllBooks()

        binding.recyclerCatalogo.setHasFixedSize(true)
        binding.recyclerCatalogo.layoutManager = LinearLayoutManager(requireContext())

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