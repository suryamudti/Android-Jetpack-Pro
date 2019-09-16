package com.surya.androidjetpackpro.data.remote

import com.surya.androidjetpackpro.data.remote.responses.TVShowResponse
import com.surya.androidjetpackpro.BuildConfig
import com.surya.androidjetpackpro.data.remote.responses.MoviesResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * Created by suryamudti on 07/09/2019.
 */

interface MyApiService {

    @GET("discover/movie")
    suspend fun getMovies() : Response<MoviesResponse>

    @GET("discover/tv")
    suspend fun getTVShows() : Response<TVShowResponse>


    companion object{
        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ) : MyApiService {

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BuildConfig.TMDB_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApiService::class.java)
        }
    }
}