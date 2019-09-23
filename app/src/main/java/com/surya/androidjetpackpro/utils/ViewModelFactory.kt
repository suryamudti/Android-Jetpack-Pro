package com.surya.androidjetpackpro.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.surya.androidjetpackpro.data.repositories.AppRepository
import com.surya.androidjetpackpro.ui.favorite.FavoriteMovieViewModel
import com.surya.androidjetpackpro.ui.favorite.FavoriteTvShowViewModel
import com.surya.androidjetpackpro.ui.movie.MovieViewModel
import com.surya.androidjetpackpro.ui.tvshow.TvshowViewModel

/**
 * Created by suryamudti on 22/09/2019.
 */
class ViewModelFactory constructor(private val repository: AppRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(MovieViewModel::class.java) ->
                    MovieViewModel(repository)
                isAssignableFrom(TvshowViewModel::class.java) ->
                    TvshowViewModel(repository)
                isAssignableFrom(FavoriteMovieViewModel::class.java) ->
                    FavoriteMovieViewModel(repository)
                isAssignableFrom(FavoriteTvShowViewModel::class.java) ->
                    FavoriteTvShowViewModel(repository)
                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
}
