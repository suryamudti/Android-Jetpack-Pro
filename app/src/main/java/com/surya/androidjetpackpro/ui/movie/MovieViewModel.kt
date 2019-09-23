package com.surya.androidjetpackpro.ui.movie

import androidx.lifecycle.ViewModel
import com.surya.androidjetpackpro.data.repositories.AppRepository

/**
 * Created by suryamudti on 08/09/2019.
 */
class MovieViewModel(
    private val repository: AppRepository
) : ViewModel(){

    val movieList = repository.getRemoteMovies()
}