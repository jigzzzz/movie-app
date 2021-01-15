package com.exercise.movie_app.data.model

import com.google.gson.annotations.SerializedName

data class DetailMovie(
    @SerializedName("id")
    val id: Int,
    @SerializedName("original_title")
    val title: String,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("tagline")
    val tagLine: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("vote_average")
    val voteAvg: String,
    @SerializedName("imdb_id")
    val imdbId: String
)