package com.surya.androidjetpackpro.data.repositories

import com.surya.androidjetpackpro.data.local.MyDatabase
import com.surya.androidjetpackpro.data.models.Movie
import com.surya.androidjetpackpro.data.remote.MyApiService
import com.surya.androidjetpackpro.data.remote.SafeApiRequest
import com.surya.androidjetpackpro.data.remote.responses.MoviesResponse

/**
 * Created by suryamudti on 08/09/2019.
 */

class MovieRepository(
    private val api : MyApiService,
    private val db : MyDatabase
    ) : SafeApiRequest(){

    // API
    suspend fun getMovies() : MoviesResponse {
        return apiRequest {
            api.getMovies()
        }
    }

    // local Movies
    suspend fun saveMovies(movie: Movie){
        db.getFavoriteMovieDao().insertMovie(movie)
    }

    fun deleteMovie(movie: Movie){
        db.getFavoriteMovieDao().deleteMovies(movie)
    }

    fun getAllMovies() = db.getFavoriteMovieDao().getAllMovies()

    fun getSingleMovie(id : Int) = db.getFavoriteMovieDao().getSingleMovie(id)
}