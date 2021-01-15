package com.exercise.movie_app.data.model.response

import com.google.gson.annotations.SerializedName

data class MovieResponse<T>(
    @SerializedName("results")
    val result: List<T>
)