package com.tp3.tp3_thebookbox.viewModels

import androidx.lifecycle.ViewModel
import com.tp3.tp3_thebookbox.databinding.FragmentAddBookBinding
import com.tp3.tp3_thebookbox.entities.Book

class AddBookViewModel : ViewModel() {
    var options : MutableList<String> = mutableListOf()
}