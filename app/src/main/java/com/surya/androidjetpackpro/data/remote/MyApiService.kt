package com.surya.androidjetpackpro.data.remote

import com.surya.androidjetpackpro.data.remote.responses.TVShowResponse
import com.surya.androidjetpackpro.BuildConfig
import com.surya.androidjetpackpro.data.remote.responses.MoviesResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by suryamudti on 07/09/2019.
 */

interface MyApiService {

    @GET("discover/movie")
    suspend fun getMovies() : Response<MoviesResponse>

    @GET("movie/popular")
    fun getMoviesByPage(
        @Query("api_key") apiKey: String? = BuildConfig.MOVIE_API_KEY,
        @Query("page") page: Int
    ) : Call<MoviesResponse>

    @GET("discover/tv")
    suspend fun getTVShows() : Response<TVShowResponse>

    @GET("tv/popular")
    fun getTVShowsByPage(
        @Query("api_key") apiKey: String? = BuildConfig.MOVIE_API_KEY,
        @Query("page") page: Int
    ) : Call<TVShowResponse>


    companion object{

        fun create() : MyApiService{
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BASIC

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(logger)
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