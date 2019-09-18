package com.surya.androidjetpackpro.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.surya.androidjetpackpro.data.local.dao.FavoriteMovieDao
import com.surya.androidjetpackpro.data.local.dao.FavoriteTvSeriesDao
import com.surya.androidjetpackpro.data.models.Movie
import com.surya.androidjetpackpro.data.models.TVShow

/**
 * Created by suryamudti on 18/09/2019.
 */
@Database(
    entities = [Movie::class, TVShow::class],
    version = 1
)
abstract class MyDatabase : RoomDatabase() {

    abstract fun getFavoriteMovieDao() : FavoriteMovieDao
    abstract fun getFavoriteTVShowDao() : FavoriteTvSeriesDao

    companion object{
        @Volatile
        private var instance: MyDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance?:buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) =  Room.databaseBuilder(
            context.applicationContext,
            MyDatabase::class.java,
            "jetpackpro.db"
        ).build()
    }
}