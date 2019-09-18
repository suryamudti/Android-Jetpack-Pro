package com.surya.androidjetpackpro.data.remote

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.surya.androidjetpackpro.BuildConfig
import com.surya.androidjetpackpro.utils.NoInternetException
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by suryamudti on 06/08/2019.
 */
class NetworkConnectionInterceptor(context: Context): Interceptor {

    private val applicationContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isInternetAvailable(applicationContext)){
            throw NoInternetException("Please check the network")
        }

        val original = chain.request()
        val httpUrl = original.url()

        val newHttpUrl = httpUrl.newBuilder()
            .addQueryParameter("api_key", BuildConfig.MOVIE_API_KEY)
            .addQueryParameter("language","en-US")
            .build()
        val requestBuilder = original.newBuilder().url(newHttpUrl)


        return chain.proceed(requestBuilder.build())
    }

    @Suppress("DEPRECATION")
    fun isInternetAvailable(context: Context): Boolean {
        var result = false
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cm?.run {
                cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                    result = when {
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                        else -> false
                    }
                }
            }
        } else {
            cm?.run {
                cm.activeNetworkInfo?.run {
                    if (type == ConnectivityManager.TYPE_WIFI) {
                        result = true
                    } else if (type == ConnectivityManager.TYPE_MOBILE) {
                        result = true
                    }
                }
            }
        }
        return result
    }

}