package com.surya.androidjetpackpro.data.repositories

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.surya.androidjetpackpro.data.models.Movie
import com.surya.androidjetpackpro.data.models.TVShow

/**
 * Created by suryamudti on 22/09/2019.
 */
interface AppDataSource {

    fun getRemoteMovies() : LiveData<PagedList<Movie>>

    fun getRemoteTvShow() : LiveData<PagedList<TVShow>>


    fun getLocalMovies() : DataSource.Factory<Int,Movie>

    fun insertLocalMovie(data: Movie)

    fun deleteLocalMovie(data: Movie)


    fun getLocalTvShow() : DataSource.Factory<Int,TVShow>

    fun inserLocalTvShow(data: TVShow)

    fun deleteLocalTvShow(data: TVShow)





}