package com.tp3.tp3_thebookbox.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.tp3.tp3_thebookbox.R
import com.tp3.tp3_thebookbox.adapters.CatalogueAdapter
import com.tp3.tp3_thebookbox.adapters.FavsAdapter
import com.tp3.tp3_thebookbox.databinding.FragmentFavouriteBooksBinding
import com.tp3.tp3_thebookbox.viewModels.FavouriteBooksViewModel

class FavouriteBooksFragment : Fragment() {

    private lateinit var binding: FragmentFavouriteBooksBinding
    private val viewModel: FavouriteBooksViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavouriteBooksBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        //viewModel.addBooks()
        viewModel.getFavBooks(binding)
        binding.recyclerCatalogo.setHasFixedSize(true)
        binding.recyclerCatalogo.layoutManager = LinearLayoutManager(context)

    }
}