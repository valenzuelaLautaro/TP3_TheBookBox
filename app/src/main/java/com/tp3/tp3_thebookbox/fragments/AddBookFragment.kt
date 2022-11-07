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
    private var idBook: Int = 5
    lateinit var path : Uri
    lateinit var downloadURL :String

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

        val parentJob = Job()
        val scope = CoroutineScope(Dispatchers.Default + parentJob)


        binding.inputUrlImage.setOnClickListener {
            // corutinas
            scope.launch {
                openFile()
                downloadURL = viewModel.uploadPhoto(path)
                cargarImagen(downloadURL)
            }
        }



        binding.publishBookButton.setOnClickListener {

            val book = Book(idBook, binding.inputTitle.text.toString(),
                binding.inputAutor.text.toString(),
                Date(binding.inputEdicion.text.toString().toInt().toLong()),
                binding.inputGenero.text.toString(),
                binding.inputEditorial.text.toString(),
                downloadURL ,
                user.email)

            if(viewModel.validateForm(binding)){
                viewModel.publishBook(book)
                //viewModel.uploadBook()
                //viewModel.uploadPhoto()
                println("TODO OK")
            }
            idBook++
        }
    }
    private suspend fun openFile(){
        TedImagePicker.with(requireContext())
            .start { uri ->
                Log.d("uri de la foto",uri.toString())
                path = uri
            }
        delay(20000)
    }

    private suspend fun cargarImagen(downloadPath:String){
            Glide.with(binding.root).load(downloadPath).centerCrop().into(binding.portada)
    }


}