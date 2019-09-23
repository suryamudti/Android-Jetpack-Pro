package com.surya.androidjetpackpro.data.paging

import androidx.paging.PageKeyedDataSource
import com.surya.androidjetpackpro.data.models.Movie
import com.surya.androidjetpackpro.data.remote.MyApiService
import com.surya.androidjetpackpro.data.remote.responses.MoviesResponse
import com.surya.androidjetpackpro.utils.Coroutines
import com.surya.androidjetpackpro.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by suryamudti on 22/09/2019.
 */

class MovieDataSource(
    private val api: MyApiService
) : PageKeyedDataSource<Int, Movie>(){
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Movie>
    ) {
        EspressoIdlingResource.increment()
        Coroutines.main {
            api.getMoviesByPage(page = 1).enqueue(
                object : Callback<MoviesResponse>{
                    override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                        EspressoIdlingResource.decrement()
                    }

                    override fun onResponse(
                        call: Call<MoviesResponse>,
                        response: Response<MoviesResponse>
                    ) {
                        EspressoIdlingResource.decrement()
                        if (response.isSuccessful){
                            val data = response.body()?.results ?: emptyList()
                            callback.onResult(data,null,  2)
                        }
                    }
                }
            )
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        EspressoIdlingResource.increment()
        Coroutines.main {
            api.getMoviesByPage(page = params.key).enqueue(
                object : Callback<MoviesResponse>{
                    override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                        EspressoIdlingResource.decrement()
                    }

                    override fun onResponse(
                        call: Call<MoviesResponse>,
                        response: Response<MoviesResponse>
                    ) {
                        EspressoIdlingResource.decrement()
                        if (response.isSuccessful){
                            val data = response.body()?.results ?: emptyList()

                            callback.onResult(data, params.key+1)
                        }
                    }
                }
            )
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {}

}