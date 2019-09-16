package com.surya.androidjetpackpro.data.repositories

import com.surya.androidjetpackpro.data.remote.MyApiService
import com.surya.androidjetpackpro.data.remote.SafeApiRequest
import com.surya.androidjetpackpro.data.remote.responses.TVShowResponse


/**
 * Created by suryamudti on 08/09/2019.
 */

class TvShowRepository(
    private val api : MyApiService
) : SafeApiRequest() {
    suspend fun getTVShows() : TVShowResponse {
        return apiRequest {
            api.getTVShows()
        }
    }
}