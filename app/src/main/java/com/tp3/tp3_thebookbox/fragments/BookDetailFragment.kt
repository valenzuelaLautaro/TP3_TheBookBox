package com.tp3.tp3_thebookbox.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.tp3.tp3_thebookbox.R
import com.tp3.tp3_thebookbox.databinding.ActivityMainBinding
import com.tp3.tp3_thebookbox.databinding.FragmentBookDetailBinding
import com.tp3.tp3_thebookbox.databinding.FragmentCatalogueBinding
import com.tp3.tp3_thebookbox.viewModels.BookDetailViewModel
import com.tp3.tp3_thebookbox.viewModels.FavouriteBooksViewModel

class BookDetailFragment : Fragment() {

    private lateinit var binding: FragmentBookDetailBinding
    private val viewModel: BookDetailViewModel by viewModels()
    val email: String = "martin.blasson@gmail.com"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBookDetailBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        val book = BookDetailFragmentArgs.fromBundle(requireArguments()).bookSelected

        viewModel.setBookDetail(book, binding)

        binding.addFavorites.setOnClickListener {
            viewModel.addFavouriteBook(book, email)
        }

        binding.deleteButton.setOnClickListener {
            viewModel.deleteBook(BookDetailFragmentArgs.fromBundle(requireArguments()).bookSelected)
        }
        binding.addFavorites.setOnClickListener {
            viewModel.addFavouriteBook(BookDetailFragmentArgs.fromBundle(requireArguments()).bookSelected, email)
        }
    }
}