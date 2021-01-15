package com.exercise.movie_app.external.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.exercise.movie_app.BuildConfig
import com.exercise.movie_app.data.model.Movie
import com.exercise.movie_app.data.model.SlideMovie
import com.exercise.movie_app.databinding.LayoutPopularMovieBinding
import com.exercise.movie_app.databinding.LayoutUpcomingMovieBinding

class GridAdapter : RecyclerView.Adapter<GridAdapter.GridViewHolder>() {

    var onItemClickListener : OnItemClickListener? = null

    interface OnItemClickListener {
        fun onClick(movie: Movie)
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    val movies: MutableList<Movie> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder {
        return GridViewHolder(LayoutUpcomingMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    fun setMovies(popularMovies: List<Movie>) {
        movies.addAll(popularMovies)
        notifyDataSetChanged()
    }

    inner class GridViewHolder(private val binding: LayoutUpcomingMovieBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            binding.title = movie.title
            Glide.with(binding.root)
                .load(BuildConfig.POSTER_URL + movie.posterPath)
                .into(binding.ivPoster)

            binding.cardUpcomingMovie.setOnClickListener {
                onItemClickListener?.onClick(movie)
            }
        }
    }

}