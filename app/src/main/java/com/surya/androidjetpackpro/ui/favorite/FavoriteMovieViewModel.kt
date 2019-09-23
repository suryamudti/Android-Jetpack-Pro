package com.surya.androidjetpackpro.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.surya.androidjetpackpro.data.models.Movie
import com.surya.androidjetpackpro.data.models.TVShow
import com.surya.androidjetpackpro.data.repositories.AppRepository

/**
 * Created by suryamudti on 22/09/2019.
 */
class FavoriteMovieViewModel(
    private val repository: AppRepository
) : ViewModel(){

    private val dataMovies = repository.getLocalMovies()
    private val liveDataMovies = LivePagedListBuilder(dataMovies, 10).build()

    val favMovies: LiveData<PagedList<Movie>> get() = liveDataMovies


    fun addToFavorite(data: Movie){
        repository.insertLocalMovie(data)
    }

    fun deleteFromFavorite(data: Movie){
        repository.deleteLocalMovie(data)
    }

    fun getSingle(id: Int): LiveData<Movie>{
        return repository.getSingleMovie(id)
    }
}