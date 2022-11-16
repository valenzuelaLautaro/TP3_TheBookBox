package com.tp3.tp3_thebookbox.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tp3.tp3_thebookbox.R
import com.tp3.tp3_thebookbox.entities.Comment

class CommentAdapter (var commentList: MutableList<Comment>) : RecyclerView.Adapter<CommentAdapter.CommentHolder>(){

    class CommentHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view : View
        init {
            view = v
        }
       fun setComment(comment: Comment){
           var text : TextView = view.findViewById(R.id.bookComment)
           var stars : TextView = view.findViewById(R.id.bookStars)

           text.text = comment.text
           stars.text = comment.stars.toString()

       }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentAdapter.CommentHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.comment_item, parent, false)
        return (CommentHolder(view))
    }

    override fun onBindViewHolder(holder: CommentAdapter.CommentHolder, position: Int) {
        holder.setComment(commentList[position])
    }

    override fun getItemCount(): Int {
        return commentList.size
    }

}