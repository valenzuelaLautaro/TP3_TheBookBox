package com.tp3.tp3_thebookbox.viewModels

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.tp3.tp3_thebookbox.adapters.CommentAdapter
import com.tp3.tp3_thebookbox.databinding.FragmentCommentBinding
import com.tp3.tp3_thebookbox.entities.Book
import com.tp3.tp3_thebookbox.entities.Comment
import com.tp3.tp3_thebookbox.fragments.CommentFragmentDirections
import com.tp3.tp3_thebookbox.fragments.FavouriteBooksFragmentDirections

class CommentViewModel : ViewModel() {

    lateinit var adapter : CommentAdapter
    val db = Firebase.firestore
    var commentList : MutableList<Comment> = mutableListOf()
    var idComment : Int = 0

    fun validateForm(binding: FragmentCommentBinding): Boolean {
        var isValid = true

        with(binding){
            if(inputComment.text.toString().isEmpty()){
                isValid = false
                inputComment.error = "Campo requerido."
            }
            if(inputStars.text.toString().isEmpty()){
                isValid = false
                inputStars.error = "Campo requerido."
            } else if(inputStars.text.toString().toString().toInt()> 5 ||  inputStars.text.toString().toString().toInt()< 1){
                isValid = false
                inputStars.error = "Ingrese un numero del 1 al 5."
            }
        }

        return isValid
    }

    fun addComment(comment: Comment){
        db.collection("comments")
            .document(comment.idComment)
            .set(comment)
            .addOnCompleteListener{
                Log.d(TAG, "Comentario agregado correctamente.")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "El comentario no pudo ser agregado.", e)
            }
    }

    fun getAllComments(idBook: String, binding: FragmentCommentBinding){
        db.collection("comments")
            //.whereEqualTo("idBook", idBook)
            .get()
            .addOnSuccessListener { resultado ->
                for(document in resultado){
                    var comment : Comment = document.toObject<Comment>()
                    println("COMENTARIO: " + comment)
                    commentList.add(comment)
                }
                Log.d(TAG, "COMENTARIOS " + commentList.toString())

                adapter = CommentAdapter(commentList)
                binding.recComments.adapter = adapter
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error al cargar los comentarios", e)
            }
    }

    fun getLastId() {
        db.collection("comments")
            .get()
            .addOnSuccessListener {
                idComment = it.documents.size
                Log.d(TAG, "COMMENTS SIZE: " + it.documents.size)
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error al cargar los comentarios", e)
            }
    }

    fun navigateToBookDetail(book: Book, binding: FragmentCommentBinding) {
        var action = CommentFragmentDirections.actionCommentFragmentToBookDetailFragment(book)
        binding.root.findNavController().navigate(action)
    }


}