package com.exercise.movie_app.data.repository.remote

import com.exercise.movie_app.BuildConfig
import com.exercise.movie_app.data.model.Comment
import com.exercise.movie_app.data.model.DetailMovie
import com.exercise.movie_app.data.model.Movie
import com.exercise.movie_app.data.model.SlideMovie
import com.exercise.movie_app.data.model.response.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieService {
    @GET("popular?api_key=${BuildConfig.API_KEY}")
    fun findAllPopularMovies() : Call<MovieResponse<SlideMovie>>

    @GET("upcoming?api_key=${BuildConfig.API_KEY}")
    fun findAllUpcomingMovies() : Call<MovieResponse<Movie>>

    @GET("{id}?api_key=${BuildConfig.API_KEY}")
    fun findMovieById(@Path("id") id: Int) : Call<DetailMovie>

    @GET("{id}/reviews?api_key=${BuildConfig.API_KEY}")
    fun findMovieReviewById(@Path("id") id: Int) : Call<MovieResponse<Comment>>
}