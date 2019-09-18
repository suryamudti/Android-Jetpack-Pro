package com.surya.androidjetpackpro.data.local.dao

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.room.*
import com.surya.androidjetpackpro.data.models.Movie

/**
 * Created by suryamudti on 18/09/2019.
 */

@Dao
interface FavoriteMovieDao {
    @Insert
    suspend fun insertMovie( movie : Movie)

    @Query("SELECT * FROM  Movie")
    fun getAllMovies() : LiveData<List<Movie>>

    @Query("SELECT * FROM  Movie")
    fun getMoviesFromWidget() : List<Movie>

    @Query("SELECT * FROM Movie WHERE id = :id")
    fun getSingleMovie(id : Int) : LiveData<Movie>

    @Query("SELECT * FROM Movie WHERE id = :id")
    fun getSingle(id : Int) : Movie

    @Delete
    fun deleteMovies(movie: Movie) : Int

    @Query("SELECT * FROM  Movie")
    fun getAllMoviesProvider() : Cursor

    @Query("SELECT * FROM  Movie WHERE id = :id")
    fun getAllMoviesProviderById(id : Int) : Cursor

    @Update
    fun update (movie: Movie) : Int
}