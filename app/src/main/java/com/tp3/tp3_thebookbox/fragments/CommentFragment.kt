package com.tp3.tp3_thebookbox.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.tp3.tp3_thebookbox.R
import com.tp3.tp3_thebookbox.databinding.FragmentCommentBinding
import com.tp3.tp3_thebookbox.entities.Comment
import com.tp3.tp3_thebookbox.viewModels.CommentViewModel

class CommentFragment : Fragment() {

    private lateinit var binding: FragmentCommentBinding
    private val viewModel: CommentViewModel by viewModels()
    private val idUser : String = "lautarovalenzuela94@gmail.com"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCommentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        val book = CommentFragmentArgs.fromBundle(requireArguments()).book

        viewModel.getLastId()

        viewModel.getAllComments(book.id, binding)

        binding.submitComment.setOnClickListener {
            val text = binding.inputComment.text.toString()
            var stars = binding.inputStars.text.toString()

            if(stars.isEmpty()){
                stars = "1"
            }

            val comment = Comment(viewModel.idComment.toString(), text, stars.toInt(), idUser, book.id)

            if(viewModel.validateForm(binding)){
                viewModel.addComment(comment)
                viewModel.navigateToBookDetail(book, binding)
            }
        }
    }

}