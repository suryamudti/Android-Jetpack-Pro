package com.surya.androidjetpackpro.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.surya.androidjetpackpro.data.models.Movie
import com.surya.androidjetpackpro.data.models.TVShow
import com.surya.androidjetpackpro.data.repositories.TvShowRepository
import com.surya.androidjetpackpro.utils.ApiException
import com.surya.androidjetpackpro.utils.Constants
import com.surya.androidjetpackpro.utils.Coroutines
import com.surya.androidjetpackpro.utils.EspressoIdlingResource

class TvshowViewModel(
    private val repository: TvShowRepository
) : ViewModel() {
    private lateinit var  listTVShows : MutableLiveData<List<TVShow>>

    var tvShowListener : TVShowListener ?= null

    /*API*/
    fun getTVShows(type: Int): LiveData<List<TVShow>>{
        if (!::listTVShows.isInitialized || type == Constants.REFRESH){
            listTVShows = MutableLiveData()
            tvShowListener?.onStarted()
            EspressoIdlingResource.increment()
            Coroutines.main{
                try {
                    val response = repository.getTVShows()

                    response.results.let {
                        listTVShows.value = it
                        tvShowListener?.onSuccess(it)
                        return@main
                    }
                }catch (e: ApiException){
                    e.message?.let { tvShowListener?.onFailure(it) }
                }
            }
        }
        return listTVShows
    }
}
