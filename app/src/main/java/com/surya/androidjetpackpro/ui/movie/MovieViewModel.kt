package com.surya.androidjetpackpro.ui.movie

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.surya.androidjetpackpro.data.models.Movie
import com.dicoding.surya.mademovieapp.ui.movie.MovieListener
import com.surya.androidjetpackpro.data.repositories.MovieRepository
import com.surya.androidjetpackpro.utils.ApiException
import com.surya.androidjetpackpro.utils.Constants
import com.surya.androidjetpackpro.utils.Coroutines
import com.surya.androidjetpackpro.utils.EspressoIdlingResource

/**
 * Created by suryamudti on 08/09/2019.
 */
class MovieViewModel(
    private val repository: MovieRepository
) : ViewModel(){

    private lateinit var listMovies : MutableLiveData<List<Movie>>

    var movieListener : MovieListener?= null

    /*API*/
    fun getMovies(type: Int) : LiveData<List<Movie>>{
        if (!::listMovies.isInitialized || type == Constants.REFRESH){
            listMovies = MutableLiveData()
            movieListener?.onStarted()
            EspressoIdlingResource.increment()
            Coroutines.main {
                try {
                    Log.e("datass","passeddd type "+type+" value "+listMovies.value)
                    val response = repository.getMovies()
                    response.results.let {
                        listMovies.postValue(it)
                        movieListener?.onSuccess(it)
                        return@main
                    }
                }catch (e: ApiException){
                    movieListener?.onFailure(e.message!!)
                }
            }
        }
        return listMovies
    }

}