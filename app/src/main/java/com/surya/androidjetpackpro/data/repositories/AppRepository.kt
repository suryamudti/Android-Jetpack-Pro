package com.surya.androidjetpackpro.data.repositories

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.surya.androidjetpackpro.data.local.LocalRepository
import com.surya.androidjetpackpro.data.local.MyDatabase
import com.surya.androidjetpackpro.data.models.Movie
import com.surya.androidjetpackpro.data.models.TVShow
import com.surya.androidjetpackpro.data.remote.MyApiService
import com.surya.androidjetpackpro.data.remote.RemoteRepository
import com.surya.androidjetpackpro.data.remote.SafeApiRequest
import com.surya.androidjetpackpro.data.remote.responses.MoviesResponse

/**
 * Created by suryamudti on 08/09/2019.
 */

class AppRepository(
    private val local: LocalRepository,
    private val remote: RemoteRepository
) : AppDataSource {

    // REMOTE
    override fun getRemoteMovies(): LiveData<PagedList<Movie>> {
        return remote.getMovieFromApi()
    }

    override fun getRemoteTvShow(): LiveData<PagedList<TVShow>> {
        return remote.getTvShowFromApi()
    }


    // LOCAL STORAGE
    override fun getLocalMovies(): DataSource.Factory<Int, Movie> {
        return local.getMoviesFavorite()
    }

    override fun insertLocalMovie(data: Movie) {
        local.addMovie(data)
    }

    override fun deleteLocalMovie(data: Movie) {
        local.deleteMovie(data)
    }

    fun getSingleMovie(id : Int) : LiveData<Movie> = local.getSingleMovie(id)

    override fun getLocalTvShow(): DataSource.Factory<Int, TVShow> {
        return local.getTvShowFavorite()
    }

    override fun inserLocalTvShow(data: TVShow) {
        local.addTvShow(data)
    }

    override fun deleteLocalTvShow(data: TVShow) {
        local.deleteTvShow(data)
    }

    fun getSingleTvShow(id: Int) : LiveData<TVShow> = local.getSingleTVShow(id)



}