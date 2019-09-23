package com.surya.androidjetpackpro.data.paging

import androidx.paging.PageKeyedDataSource
import com.surya.androidjetpackpro.data.models.TVShow
import com.surya.androidjetpackpro.data.remote.MyApiService
import com.surya.androidjetpackpro.data.remote.responses.TVShowResponse
import com.surya.androidjetpackpro.utils.Coroutines
import com.surya.androidjetpackpro.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by suryamudti on 22/09/2019.
 */
class TvShowDataSource(
    private val api : MyApiService
) : PageKeyedDataSource<Int,TVShow>(){

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, TVShow>
    ) {
        EspressoIdlingResource.increment()
        Coroutines.main {
            api.getTVShowsByPage(page = 1).enqueue(
                object : Callback<TVShowResponse> {
                    override fun onFailure(call: Call<TVShowResponse>, t: Throwable) {
                        EspressoIdlingResource.decrement()
                    }

                    override fun onResponse(
                        call: Call<TVShowResponse>,
                        response: Response<TVShowResponse>
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

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, TVShow>) {
        Coroutines.main {
            api.getTVShowsByPage(page = params.key).enqueue(
                object : Callback<TVShowResponse> {
                    override fun onFailure(call: Call<TVShowResponse>, t: Throwable) {
                        EspressoIdlingResource.decrement()
                    }

                    override fun onResponse(
                        call: Call<TVShowResponse>,
                        response: Response<TVShowResponse>
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

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, TVShow>) {}
}