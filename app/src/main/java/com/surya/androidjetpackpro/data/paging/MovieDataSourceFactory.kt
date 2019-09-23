package com.surya.androidjetpackpro.data.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.surya.androidjetpackpro.data.models.Movie
import com.surya.androidjetpackpro.data.remote.MyApiService

/**
 * Created by suryamudti on 19/09/2019.
 */
class MovieDataSourceFactory(
    private val api : MyApiService
) : DataSource.Factory<Int, Movie>(){

    private val dataSourceLiveData = MutableLiveData<MovieDataSource>()

    override fun create(): DataSource<Int, Movie> {
        val source = MovieDataSource(api)
        dataSourceLiveData.postValue(source)
        return source
    }

}