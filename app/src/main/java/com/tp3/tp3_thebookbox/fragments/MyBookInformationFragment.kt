package com.tp3.tp3_thebookbox.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.tp3.tp3_thebookbox.databinding.FragmentMyBookInformationBinding
import com.tp3.tp3_thebookbox.viewModels.MyBookInformationViewModel

class MyBookInformationFragment : Fragment() {

    private lateinit var binding: FragmentMyBookInformationBinding
    private val viewModel: MyBookInformationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyBookInformationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.setBookInfo(BookDetailFragmentArgs.fromBundle(requireArguments()).bookSelected, binding)
    }

}


