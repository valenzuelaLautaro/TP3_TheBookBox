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

class FavsAdapter (var bookList: MutableList<Book>, var onClickListener: (Book) -> Unit): RecyclerView.Adapter<FavsAdapter.FavsHolder>(){

    class FavsHolder(v: View, ) : RecyclerView.ViewHolder(v) {
        private var view : View
        init {
            view = v
        }
        fun setBook(name: String, author: String){
            var bookName : TextView = view.findViewById(R.id.bookComment)
            var bookAuthor : TextView = view.findViewById(R.id.bookStars)

            bookName.text = name
            bookAuthor.text = author

        }
        fun setFrontPage(image: String){
            var bookImage : ImageView = view.findViewById(R.id.bookImage)
            Glide.with(view).load(image).centerCrop().into(bookImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavsAdapter.FavsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.book_item, parent, false)
        return (FavsAdapter.FavsHolder(view))
    }

    override fun onBindViewHolder(holder: FavsAdapter.FavsHolder, position: Int) {
        holder.setBook(bookList[position].nombre, bookList[position].autor)
        holder.setFrontPage(bookList[position].urlImage)
        holder.itemView.setOnClickListener{
            onClickListener(bookList[position])
        }
    }

    override fun getItemCount(): Int {
        return bookList.size
    }
}