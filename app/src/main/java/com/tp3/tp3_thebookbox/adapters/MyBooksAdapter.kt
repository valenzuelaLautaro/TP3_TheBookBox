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

class MyBooksAdapter (var bookList: MutableList<Book>, var onClickListener: (Book) -> Unit): RecyclerView.Adapter<MyBooksAdapter.MyBooksHolder>(){

    class MyBooksHolder(v: View, ) : RecyclerView.ViewHolder(v){
        private var view : View
        init {
            view = v
        }
        fun setBook(name: String){
            var bookName : TextView = view.findViewById(R.id.bookName)
            bookName.text = name

        }
        fun setFrontPage(image: String){
            var bookImage : ImageView = view.findViewById(R.id.bookImage)
            Glide.with(view).load(image).centerCrop().into(bookImage)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyBooksHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.book_item, parent, false)
        return (MyBooksHolder(view))
    }

    override fun onBindViewHolder(holder: MyBooksHolder, position: Int) {
        holder.setBook(bookList[position].nombre)
        holder.setFrontPage(bookList[position].urlImage)
        // este es el click listener que voy a recibir por parametro para la navegacion
        holder.itemView.setOnClickListener{
            onClickListener(bookList[position])
        }
    }

    override fun getItemCount(): Int {
        return bookList.size
    }


}