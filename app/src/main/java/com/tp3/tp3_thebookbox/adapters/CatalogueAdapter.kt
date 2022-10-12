package com.tp3.tp3_thebookbox.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tp3.tp3_thebookbox.R
import com.tp3.tp3_thebookbox.entities.Book

class CatalogueAdapter (var bookList: MutableList<Book>, ) : RecyclerView.Adapter<CatalogueAdapter.CatalogueHolder>(){

    class CatalogueHolder(v: View, ) : RecyclerView.ViewHolder(v) {
        private var view : View
        init {
            view = v
        }
        fun setBook(name: String, author: String, image: String){
            var bookName : TextView = view.findViewById(R.id.bookName)
            var bookAuthor : TextView = view.findViewById(R.id.bookAuthor)
            var bookImage : ImageView = view.findViewById(R.id.bookImage)

            bookName.text = name
            bookAuthor.text = author
            Glide.with(view).load(image).centerCrop().into(bookImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogueHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.book_item, parent, false)
        return (CatalogueHolder(view))
    }

    override fun onBindViewHolder(holder: CatalogueHolder, position: Int) {
        holder.setBook(bookList[position].nombre, bookList[position].autor, bookList[position].urlImage)
    }

    override fun getItemCount(): Int {
        return bookList.size
    }
}