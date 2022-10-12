package com.tp3.tp3_thebookbox.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tp3.tp3_thebookbox.R
import com.tp3.tp3_thebookbox.viewModels.FavouriteBooksViewModel

class FavouriteBooksFragment : Fragment() {

    companion object {
        fun newInstance() = FavouriteBooksFragment()
    }

    private lateinit var viewModel: FavouriteBooksViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favourite_books, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FavouriteBooksViewModel::class.java)
        // TODO: Use the ViewModel
    }

}