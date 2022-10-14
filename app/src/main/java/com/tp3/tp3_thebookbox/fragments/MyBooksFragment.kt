package com.tp3.tp3_thebookbox.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.tp3.tp3_thebookbox.R
import com.tp3.tp3_thebookbox.databinding.FragmentMyBooksBinding
import com.tp3.tp3_thebookbox.viewModels.MyBooksViewModel

class MyBooksFragment : Fragment() {

    private lateinit var binding: FragmentMyBooksBinding
    private val viewModel: MyBooksViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyBooksBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onStart() {
        super.onStart()
    }
}