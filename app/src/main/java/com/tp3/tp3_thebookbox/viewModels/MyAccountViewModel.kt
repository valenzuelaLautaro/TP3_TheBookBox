package com.tp3.tp3_thebookbox.viewModels

import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.tp3.tp3_thebookbox.databinding.FragmentMyAccountBinding
import com.tp3.tp3_thebookbox.fragments.MyAccountFragment
import com.tp3.tp3_thebookbox.fragments.MyAccountFragmentDirections

class MyAccountViewModel : ViewModel() {

    fun navigateToBookList (binding: FragmentMyAccountBinding){
        var action = MyAccountFragmentDirections.actionMyAccountFragmentToMyBooksFragment()
        binding.root.findNavController().navigate(action)
    }
    fun logout (binding: FragmentMyAccountBinding){
        var action = MyAccountFragmentDirections.actionMyAccountFragmentToLogInFragment2()
        binding.root.findNavController().navigate(action)
    }
}