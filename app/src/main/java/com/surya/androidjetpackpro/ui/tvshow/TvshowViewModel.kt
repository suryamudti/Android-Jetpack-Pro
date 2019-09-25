package com.surya.androidjetpackpro.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.surya.androidjetpackpro.data.models.TVShow
import com.surya.androidjetpackpro.data.repositories.AppRepository

class TvshowViewModel(
    private val repository: AppRepository
) : ViewModel() {

    fun getTvShow() : LiveData<PagedList<TVShow>>{

        val list = repository.getRemoteTvShow()

        return list
    }
}
