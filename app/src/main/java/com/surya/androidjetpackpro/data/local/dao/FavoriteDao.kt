package com.surya.androidjetpackpro.data.local.dao

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.surya.androidjetpackpro.data.models.Movie
import com.surya.androidjetpackpro.data.models.TVShow

/**
 * Created by suryamudti on 22/09/2019.
 */

@Dao
interface FavoriteDao {

    // Movie
    @Insert
    fun insertMovie( movie : Movie)

    @Query("SELECT * FROM  Movie")
    fun getAllMovies() : LiveData<List<Movie>>

    @Query("SELECT * FROM  Movie")
    fun getAllMoviesPaged() : DataSource.Factory<Int, Movie>

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


    // TV Show
    @Insert
    fun insertTVShow( tvShow: TVShow)

    @Query("SELECT * FROM  TVShow")
    fun getAllTVShows() : LiveData<List<TVShow>>

    @Query("SELECT * FROM  TVShow")
    fun getAllTVShowsPaged() : DataSource.Factory<Int, TVShow>

    @Query("SELECT * FROM TVShow WHERE id = :id")
    fun getSingleTVShow(id : Int) : LiveData<TVShow>

    @Delete
    fun deleteTVShows(tvShow: TVShow)
}