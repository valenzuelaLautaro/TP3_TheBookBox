package com.tp3.tp3_thebookbox.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.tp3.tp3_thebookbox.R
import com.tp3.tp3_thebookbox.databinding.FragmentLogInBinding
import com.tp3.tp3_thebookbox.viewModels.LogInViewModel

class LogInFragment : Fragment() {

    private lateinit var binding : FragmentLogInBinding
    private val viewModel: LogInViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLogInBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onStart() {
        super.onStart()
    }
}