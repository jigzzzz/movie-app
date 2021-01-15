package com.exercise.movie_app.ui.movie

import android.transition.Slide
import android.util.Log
import androidx.lifecycle.*
import com.exercise.movie_app.data.model.Movie
import com.exercise.movie_app.data.model.SlideMovie
import com.exercise.movie_app.data.repository.MovieRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    private val _upcomingMovies = movieRepository.findAllUpcomingMovies()
    val upcomingMovies : LiveData<List<Movie>>
        get() = _upcomingMovies

    private val _popularMovies = movieRepository.findAllPopularMovies()
    val popularMovies : LiveData<List<SlideMovie>>
        get() = _popularMovies

}