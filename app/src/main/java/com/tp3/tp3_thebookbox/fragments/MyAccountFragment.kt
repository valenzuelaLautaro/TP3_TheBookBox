package com.tp3.tp3_thebookbox.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.tp3.tp3_thebookbox.R
import com.tp3.tp3_thebookbox.databinding.FragmentMyAccountBinding
import com.tp3.tp3_thebookbox.viewModels.MyAccountViewModel

class MyAccountFragment : Fragment() {

    private lateinit var binding: FragmentMyAccountBinding
    private val viewModel: MyAccountViewModel by viewModels()
    private val email: String = "martin.blasson@gmail.com"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyAccountBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.getAccount(email, binding)
        binding.misLibrosButton.setOnClickListener {
            viewModel.navigateToBookList(binding)
        }
        binding.logoutButton.setOnClickListener {
            viewModel.logout(binding)
        }
    }
}