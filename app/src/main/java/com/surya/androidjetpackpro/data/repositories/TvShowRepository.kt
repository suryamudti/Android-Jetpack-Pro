package com.surya.androidjetpackpro.data.repositories

import com.surya.androidjetpackpro.data.local.MyDatabase
import com.surya.androidjetpackpro.data.models.TVShow
import com.surya.androidjetpackpro.data.remote.MyApiService
import com.surya.androidjetpackpro.data.remote.SafeApiRequest
import com.surya.androidjetpackpro.data.remote.responses.TVShowResponse


/**
 * Created by suryamudti on 08/09/2019.
 */

class TvShowRepository(
    private val api : MyApiService,
    private val db : MyDatabase
) : SafeApiRequest() {

    // API
    suspend fun getTVShows() : TVShowResponse {
        return apiRequest {
            api.getTVShows()
        }
    }

    // local TV SHow
    suspend fun saveTVShow(tvShow: TVShow){
        db.getFavoriteTVShowDao().insertTVShow(tvShow)
    }

    fun getAllTVShow() = db.getFavoriteTVShowDao().getAllTVShows()

    fun getSingleTVShow(id : Int) = db.getFavoriteTVShowDao().getSingleTVShow(id)

    fun deleteTVShow(tvShow: TVShow){
        db.getFavoriteTVShowDao().deleteTVShows(tvShow)
    }
}