package com.exercise.movie_app.data.model

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("id")
    val id: Int,
    @SerializedName("original_title")
    val title: String,
    @SerializedName("poster_path")
    val posterPath: String
)