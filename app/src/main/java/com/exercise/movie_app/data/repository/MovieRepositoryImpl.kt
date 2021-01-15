package com.exercise.movie_app.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.exercise.movie_app.data.model.Comment
import com.exercise.movie_app.data.model.DetailMovie
import com.exercise.movie_app.data.model.Movie
import com.exercise.movie_app.data.model.SlideMovie
import com.exercise.movie_app.data.model.response.MovieResponse
import com.exercise.movie_app.data.repository.remote.MovieService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepositoryImpl(private val movieService: MovieService) : MovieRepository {
    override fun findAllPopularMovies(): LiveData<List<SlideMovie>> {
        val movies : MutableLiveData<List<SlideMovie>> = MutableLiveData()
        movieService.findAllPopularMovies().enqueue(
            object : Callback<MovieResponse<SlideMovie>>{
                override fun onFailure(call: Call<MovieResponse<SlideMovie>>, t: Throwable) {
                    t.stackTrace
                }

                override fun onResponse(
                    call: Call<MovieResponse<SlideMovie>>,
                    response: Response<MovieResponse<SlideMovie>>
                ) {
                    if (response.isSuccessful)
                        movies.postValue(response.body()!!.result)
                }
            }
        )
        return movies
    }

    override fun findAllUpcomingMovies(): LiveData<List<Movie>> {
        val movies : MutableLiveData<List<Movie>> = MutableLiveData()
        movieService.findAllUpcomingMovies().enqueue(
            object : Callback<MovieResponse<Movie>>{
                override fun onFailure(call: Call<MovieResponse<Movie>>, t: Throwable) {
                    t.stackTrace
                }

                override fun onResponse(
                    call: Call<MovieResponse<Movie>>,
                    response: Response<MovieResponse<Movie>>
                ) {
                    if (response.isSuccessful)
                        movies.postValue(response.body()!!.result)
                }
            }
        )
        return movies
    }

    override fun findDetailMovieById(id: Int): LiveData<DetailMovie> {
        var movie : MutableLiveData<DetailMovie> = MutableLiveData()
        movieService.findMovieById(id).enqueue(
            object : Callback<DetailMovie>{
                override fun onFailure(call: Call<DetailMovie>, t: Throwable) {
                    t.stackTrace
                }

                override fun onResponse(
                    call: Call<DetailMovie>,
                    response: Response<DetailMovie>
                ) {
                    if (response.isSuccessful)
                        movie.postValue(response.body())
                }
            }
        )
        return movie
    }

    override fun findMovieReviewById(id: Int): LiveData<List<Comment>> {
        val reviews : MutableLiveData<List<Comment>> = MutableLiveData()
        movieService.findMovieReviewById(id).enqueue(
            object : Callback<MovieResponse<Comment>>{
                override fun onFailure(call: Call<MovieResponse<Comment>>, t: Throwable) {
                    t.stackTrace
                }

                override fun onResponse(
                    call: Call<MovieResponse<Comment>>,
                    response: Response<MovieResponse<Comment>>
                ) {
                    if (response.isSuccessful)
                        reviews.postValue(response.body()!!.result)
                }
            }
        )
        return reviews
    }

}