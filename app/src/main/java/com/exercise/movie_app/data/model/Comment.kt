package com.exercise.movie_app.data.model

import com.google.gson.annotations.SerializedName

data class Comment(
    @SerializedName("author")
    val author: String,
    @SerializedName("content")
    val content: String
)