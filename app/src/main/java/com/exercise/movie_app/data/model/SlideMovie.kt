package com.exercise.movie_app.data.model

import com.google.gson.annotations.SerializedName

data class SlideMovie(
    @SerializedName("id")
    val id: Int,
    @SerializedName("original_title")
    val title: String,
    @SerializedName("backdrop_path")
    val backdropPath: String
)