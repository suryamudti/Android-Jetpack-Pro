package com.surya.androidjetpackpro.data.remote

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.surya.androidjetpackpro.data.models.Movie
import com.surya.androidjetpackpro.data.models.TVShow
import com.surya.androidjetpackpro.data.paging.MovieDataSourceFactory
import com.surya.androidjetpackpro.data.paging.TvShowDataSourceFactory

/**
 * Created by suryamudti on 22/09/2019.
 */
class RemoteRepository(
    private val api : MyApiService
) {

    private val config = PagedList.Config.Builder()
        .setPageSize(5)
        .setInitialLoadSizeHint(10)
        .setEnablePlaceholders(false)
        .build()

    fun getMovieFromApi() : LiveData<PagedList<Movie>>{
        val source = MovieDataSourceFactory(api)
        return LivePagedListBuilder(source,config).build()
    }

    fun getTvShowFromApi() : LiveData<PagedList<TVShow>>{
        val source = TvShowDataSourceFactory(api)
        return LivePagedListBuilder(source,config).build()

    }

}