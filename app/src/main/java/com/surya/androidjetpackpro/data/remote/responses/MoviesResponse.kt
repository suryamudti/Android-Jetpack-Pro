package com.surya.androidjetpackpro.data.remote.responses

import com.surya.androidjetpackpro.data.models.Movie


/**
 * Created by suryamudti on 27/08/2019.
 */
data class MoviesResponse(
    val results : List<Movie>? = emptyList()
)