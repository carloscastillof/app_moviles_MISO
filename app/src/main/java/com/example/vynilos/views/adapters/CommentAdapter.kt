package com.example.vynilos.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vynilos.R
import com.example.vynilos.databinding.ItemCommentBinding
import com.example.vynilos.databinding.ItemTrackBinding
import com.example.vynilos.models.Comment
import com.example.vynilos.models.Track

class CommentAdapter: RecyclerView.Adapter<CommentAdapter.CommentHolder>() {
    private var comments: List<Comment>? = null
    fun updateComments(newComments: List<Comment>) {
        this.comments = newComments
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: CommentHolder, position: Int) {
        val item = comments?.get(position)
        holder.bind(item!!)
    }

    fun setComments(commentsList: List<Comment>) {
        this.comments = commentsList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CommentHolder(layoutInflater.inflate(R.layout.item_comment, parent, false))
    }

    override fun getItemCount(): Int {
        return if (comments == null) 0
        else comments?.size!!
    }

    class CommentHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemCommentBinding.bind(view)

        fun bind(comment: Comment){
            binding.tvCommentDescription.text = comment.description
            binding.tvCommentRating.text = comment.rating.toString()
        }
    }
}
