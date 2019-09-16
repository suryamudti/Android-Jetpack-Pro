package com.surya.androidjetpackpro.data.repositories

import com.surya.androidjetpackpro.data.remote.MyApiService
import com.surya.androidjetpackpro.data.remote.SafeApiRequest
import com.surya.androidjetpackpro.data.remote.responses.MoviesResponse

/**
 * Created by suryamudti on 08/09/2019.
 */

class MovieRepository(
    private val api : MyApiService
) : SafeApiRequest(){

    suspend fun getMovies() : MoviesResponse {
        return apiRequest {
            api.getMovies()
        }
    }
}