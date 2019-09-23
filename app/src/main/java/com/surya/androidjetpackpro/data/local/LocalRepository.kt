package com.surya.androidjetpackpro.data.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.surya.androidjetpackpro.data.local.dao.FavoriteDao
import com.surya.androidjetpackpro.data.models.Movie
import com.surya.androidjetpackpro.data.models.TVShow
import java.util.concurrent.Executor

/**
 * Created by suryamudti on 22/09/2019.
 */
class LocalRepository(
    private val dao: FavoriteDao,
    private val executor : Executor
) {

    // Movie
    fun getMoviesFavorite(): DataSource.Factory<Int, Movie>
            = dao.getAllMoviesPaged()

    fun addMovie(data: Movie)= executor.execute {dao.insertMovie(data)}

    fun deleteMovie(data: Movie) = executor.execute { dao.deleteMovies(data) }

    fun getSingleMovie(id: Int) : LiveData<Movie> = dao.getSingleMovie(id)

    // TV Show
    fun getTvShowFavorite(): DataSource.Factory<Int,TVShow>
            = dao.getAllTVShowsPaged()

    fun addTvShow(data: TVShow) = executor.execute { dao.insertTVShow(data) }

    fun deleteTvShow(data: TVShow) = executor.execute { dao.deleteTVShows(data) }

    fun getSingleTVShow(id: Int): LiveData<TVShow> = dao.getSingleTVShow(id)

}