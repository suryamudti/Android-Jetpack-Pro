package com.surya.androidjetpackpro.ui.tvshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.surya.androidjetpackpro.data.repositories.TvShowRepository
import com.surya.androidjetpackpro.ui.movie.MovieViewModel

/**
 * Created by suryamudti on 10/09/2019.
 */
class TvshowViewModelFactory(val repository: TvShowRepository) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TvshowViewModel(repository) as T
    }
}