package com.tp3.tp3_thebookbox.fragments

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.tp3.tp3_thebookbox.databinding.FragmentAddBookBinding
import com.tp3.tp3_thebookbox.entities.Book
import com.tp3.tp3_thebookbox.entities.User
import com.tp3.tp3_thebookbox.viewModels.CatalogueViewModel
import gun0912.tedimagepicker.builder.TedImagePicker
import kotlinx.coroutines.*
import java.util.*


class AddBookFragment : Fragment() {

    private lateinit var binding: FragmentAddBookBinding
    private val viewModel: CatalogueViewModel by activityViewModels()
    val storage = Firebase.storage
    val storageRef = storage.reference
    var downloadUri : String? = null

    // usuario Hardcodeado para crear un libro nuevo
    private val user = User("lautaro", "lautarovalenzuela94@gmail.com", "callefalsa123", "www.nada.png", Date(12/10/2002), "1166517457")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBookBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onStart() {
        super.onStart()


        binding.inputUrlImage.setOnClickListener {
            TedImagePicker.with(requireContext())
                .start { uri ->
                    Log.d("uri de la foto",uri.toString())

                    val portadaRef = storageRef.child("portadas/${uri.lastPathSegment}")
                    val uploadTask = portadaRef.putFile(uri)
                    Log.d("llego hasta aca", "1")
                    uploadTask.continueWithTask { task ->
                        if (!task.isSuccessful) {
                            task.exception?.let {
                                Log.d("llego hasta aca", "error")
                                throw it

                            }
                        }
                        Log.d("llego hasta aca", "2")
                        portadaRef.downloadUrl
                    }.addOnCompleteListener { task ->
                        Log.d("llego hasta aca", "3")
                        if (task.isSuccessful) {
                            downloadUri = task.result.toString()
                            Log.d("URI FIREBASE", "LA URI ES: $downloadUri")
                            Glide.with(binding.root).load(downloadUri).centerCrop().into(binding.portada)
                        } else {
                            // Handle failures
                            // ...
                            Log.d("error", "Error al enviar foto a Firebase")
                        }
                    }
                }
        }

        binding.publishBookButton.setOnClickListener {
            if (downloadUri!=null){
                val book = Book(viewModel.id++.toString(),
                    binding.inputTitle.text.toString(),
                    binding.inputAutor.text.toString(),
                    Date(binding.inputEdicion.text.toString().toInt().toLong()),
                    binding.inputGenero.text.toString(),
                    binding.inputEditorial.text.toString(),
                    downloadUri.toString(),
                    user.email)

                if(viewModel.validateForm(binding)){
                    viewModel.publishBook(book,binding)
                    println("TODO OK")
                }
            } else{
                Snackbar.make(binding.root ,"La imagen aun no fue cargada", Snackbar.LENGTH_SHORT).show()
            }

        }
    }

}