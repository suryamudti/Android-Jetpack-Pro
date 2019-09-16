package com.surya.androidjetpackpro.ui.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.surya.androidjetpackpro.data.repositories.MovieRepository

/**
 * Created by suryamudti on 27/08/2019.
 */
class MovieViewModelFactory(val repository: MovieRepository) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieViewModel(repository) as T
    }
}