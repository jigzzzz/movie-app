package com.exercise.movie_app.external.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.exercise.movie_app.BuildConfig
import com.exercise.movie_app.data.model.Comment
import com.exercise.movie_app.data.model.Movie
import com.exercise.movie_app.databinding.LayoutCommentBinding
import com.exercise.movie_app.databinding.LayoutUpcomingMovieBinding

class CommentAdapter : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    val comments: MutableList<Comment> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        return CommentViewHolder(LayoutCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = comments.size

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(comments[position])
    }

    fun setReviews(reviews: List<Comment>) {
        comments.addAll(reviews)
        notifyDataSetChanged()
    }

    inner class CommentViewHolder(private val binding: LayoutCommentBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(comment: Comment) {
            binding.apply {
                name = comment.author + ":"
                content = comment.content
            }
        }
    }

}