package com.surya.androidjetpackpro.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.surya.androidjetpackpro.data.models.Movie
import com.surya.androidjetpackpro.data.repositories.AppRepository

/**
 * Created by suryamudti on 08/09/2019.
 */
class MovieViewModel(
    private val repository: AppRepository
) : ViewModel(){

    fun getMovies() : LiveData<PagedList<Movie>>{
        val list = repository.getRemoteMovies()

        return list
    }
}