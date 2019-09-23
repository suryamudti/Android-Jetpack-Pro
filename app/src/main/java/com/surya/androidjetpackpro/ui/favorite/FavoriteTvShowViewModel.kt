package com.surya.androidjetpackpro.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.surya.androidjetpackpro.data.models.TVShow
import com.surya.androidjetpackpro.data.repositories.AppRepository

/**
 * Created by suryamudti on 22/09/2019.
 */
class FavoriteTvShowViewModel(
    private val repository: AppRepository
) : ViewModel(){

    private val dataTvShow = repository.getLocalTvShow()
    private val tvShowLiveData = LivePagedListBuilder(dataTvShow, 10).build()


    val favTvShow: LiveData<PagedList<TVShow>> get() = tvShowLiveData

    fun addToFavorite(data: TVShow){
        repository.inserLocalTvShow(data)
    }

    fun deleteFromFavorite(data: TVShow){
        repository.deleteLocalTvShow(data)
    }


    fun getSingle(id : Int): LiveData<TVShow> = repository.getSingleTvShow(id)

}