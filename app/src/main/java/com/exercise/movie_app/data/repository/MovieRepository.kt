package com.exercise.movie_app.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.exercise.movie_app.data.model.Comment
import com.exercise.movie_app.data.model.DetailMovie
import com.exercise.movie_app.data.model.Movie
import com.exercise.movie_app.data.model.SlideMovie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun findAllPopularMovies() : LiveData<List<SlideMovie>>
    fun findAllUpcomingMovies() : LiveData<List<Movie>>
    fun findDetailMovieById(id: Int) : LiveData<DetailMovie>
    fun findMovieReviewById(id: Int) : LiveData<List<Comment>>
}