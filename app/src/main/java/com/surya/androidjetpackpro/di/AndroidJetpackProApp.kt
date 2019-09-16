package com.surya.androidjetpackpro.di

import android.app.Application
import com.surya.androidjetpackpro.data.remote.MyApiService
import com.surya.androidjetpackpro.data.remote.NetworkConnectionInterceptor
import com.surya.androidjetpackpro.data.repositories.MovieRepository
import com.surya.androidjetpackpro.data.repositories.TvShowRepository
import com.surya.androidjetpackpro.ui.movie.MovieViewModelFactory
import com.surya.androidjetpackpro.ui.tvshow.TvshowViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

/**
 * Created by suryamudti on 08/09/2019.
 */
class AndroidJetpackProApp : Application(), KodeinAware {

    override val kodein =  Kodein.lazy {
        import(androidXModule(this@AndroidJetpackProApp))
        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { MyApiService(instance()) }
        bind() from singleton { MovieRepository(instance()) }
        bind() from singleton { TvShowRepository(instance()) }

        bind() from  provider { MovieViewModelFactory(instance()) }
        bind() from  provider { TvshowViewModelFactory(instance()) }

    }


}