package com.surya.androidjetpackpro.data.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.surya.androidjetpackpro.data.models.TVShow
import com.surya.androidjetpackpro.data.remote.MyApiService

/**
 * Created by suryamudti on 22/09/2019.
 */
class TvShowDataSourceFactory(
    private val api : MyApiService
) : DataSource.Factory<Int, TVShow>() {

    private val dataSourceLiveData = MutableLiveData<TvShowDataSource>()

    override fun create(): DataSource<Int, TVShow> {
        val source = TvShowDataSource(api)
        dataSourceLiveData.postValue(source)
        return source
    }
}