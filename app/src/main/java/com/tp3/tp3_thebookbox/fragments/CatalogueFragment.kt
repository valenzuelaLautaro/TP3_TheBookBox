package com.tp3.tp3_thebookbox.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tp3.tp3_thebookbox.R
import com.tp3.tp3_thebookbox.adapters.CatalogueAdapter
import com.tp3.tp3_thebookbox.entities.Book
import com.tp3.tp3_thebookbox.viewModels.CatalogueViewModel
import java.sql.Date

class CatalogueFragment : Fragment() {

    lateinit var v : View

    companion object {
        fun newInstance() = CatalogueFragment()
    }

    private lateinit var viewModel: CatalogueViewModel

    lateinit var adapter : CatalogueAdapter
    lateinit var recyclerView : RecyclerView

    var bookList : MutableList<Book> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_catalogue, container, false)

        recyclerView = v.findViewById(R.id.recyclerCatalogo)

        bookList.add(Book(0, "La iliada", "Homero", Date(27/12/2001), "Guerra", "Ninguna", "https://global-uploads.webflow.com/6034d7d1f3e0f52c50b2adee/62542902e650b33bf6b7a912_60e710db1c3ed16b7bfff0df_9788418008962_Cub.jpeg"))
        bookList.add(Book(1, "La vuelta al mundo", "Julio Verne", Date(18/12/2000), "Novela", "Viajeros", "https://www.tematika.com/media/catalog/Ilhsa/Imagenes/673611.jpg"))
        bookList.add(Book(2, "Martin Fierro", "Jose Hernandez", Date(19/6/2003), "Poesia", "Gauchos Anonimos", "https://imgserver1.fierro.com.ar/get/thumb/188/272/HERCI-QUE9879246276=9789879246276.jpg?randomize=1"))

        return v
    }

    override fun onStart() {
        super.onStart()

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)

        adapter = CatalogueAdapter(bookList)
        recyclerView.adapter = adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CatalogueViewModel::class.java)
        // TODO: Use the ViewModel
    }

}