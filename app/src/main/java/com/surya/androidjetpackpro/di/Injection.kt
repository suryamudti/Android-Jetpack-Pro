package com.surya.androidjetpackpro.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.surya.androidjetpackpro.data.local.LocalRepository
import com.surya.androidjetpackpro.data.local.MyDatabase
import com.surya.androidjetpackpro.data.remote.MyApiService
import com.surya.androidjetpackpro.data.remote.RemoteRepository
import com.surya.androidjetpackpro.data.repositories.AppRepository
import com.surya.androidjetpackpro.utils.ViewModelFactory
import java.util.concurrent.Executors

/**
 * Created by suryamudti on 22/09/2019.
 */

object Injection {
    private fun provideCache(context: Context): LocalRepository {
        val database = MyDatabase.getDatabase(context)
        return LocalRepository(database.favoriteDao, Executors.newSingleThreadExecutor())
    }

    private fun provideRemote(): RemoteRepository {
        return RemoteRepository(MyApiService.create())
    }

    private fun provideAppRepository(context: Context): AppRepository {
        return AppRepository(provideCache(context), provideRemote())
    }

    fun provideViewModelFactory(context: Context): ViewModelProvider.Factory {
        return ViewModelFactory(provideAppRepository(context))
    }
}