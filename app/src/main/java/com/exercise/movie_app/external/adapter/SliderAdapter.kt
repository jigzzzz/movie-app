package com.exercise.movie_app.external.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.exercise.movie_app.BuildConfig
import com.exercise.movie_app.data.model.SlideMovie
import com.exercise.movie_app.databinding.LayoutPopularMovieBinding

class SliderAdapter : RecyclerView.Adapter<SliderAdapter.SlideViewHolder>() {

    var onItemClickListener : OnItemClickListener? = null

    interface OnItemClickListener {
        fun onClick(movie: SlideMovie)
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    val movies: MutableList<SlideMovie> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlideViewHolder {
        return SlideViewHolder(LayoutPopularMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: SlideViewHolder, position: Int) {
        holder.bind(movies[position])
        if (position >= movies.size-2)
            holder.post(slideRunnable)
    }

    fun setMovies(popularMovies: List<SlideMovie>) {
        movies.addAll(popularMovies)
        notifyDataSetChanged()
    }

    inner class SlideViewHolder(private val binding: LayoutPopularMovieBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: SlideMovie) {
            binding.title = movie.title
            Glide.with(binding.root)
                .load(BuildConfig.POSTER_URL + movie.backdropPath)
                .into(binding.ivBackdrop)

            binding.cardUpcomingMovie.setOnClickListener {
                onItemClickListener?.onClick(movie)
            }
        }

        fun post(runnable: Runnable) {
            binding.ivBackdrop.post(runnable)
        }
    }

    private val slideRunnable = Runnable {
        setMovies(movies)
    }

}