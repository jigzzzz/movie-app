package com.exercise.movie_app.ui.detailmovie.info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.exercise.movie_app.data.model.DetailMovie
import com.exercise.movie_app.data.model.SlideMovie
import com.exercise.movie_app.data.repository.MovieRepository

class InfoViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private val _movie = MutableLiveData<DetailMovie>()
    val movie : LiveData<DetailMovie>
        get() = _movie

    fun fetchMovieDetail(id: Int) {
        movieRepository.findDetailMovieById(id).observeForever {
            _movie.postValue(it)
        }
    }

}