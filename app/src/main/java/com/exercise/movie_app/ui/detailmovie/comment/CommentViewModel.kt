package com.exercise.movie_app.ui.detailmovie.comment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.exercise.movie_app.data.model.Comment
import com.exercise.movie_app.data.model.DetailMovie
import com.exercise.movie_app.data.model.SlideMovie
import com.exercise.movie_app.data.repository.MovieRepository

class CommentViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private val _comments = MutableLiveData<List<Comment>>()
    val comments : LiveData<List<Comment>>
        get() = _comments

    fun fetchMovieReviews(id: Int) {
        movieRepository.findMovieReviewById(id).observeForever {
            _comments.postValue(it)
        }
    }

}