package com.surya.androidjetpackpro.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.surya.androidjetpackpro.data.local.dao.FavoriteDao
import com.surya.androidjetpackpro.data.models.Movie
import com.surya.androidjetpackpro.data.models.TVShow

/**
 * Created by suryamudti on 18/09/2019.
 */
@Database(
    entities = [Movie::class, TVShow::class],
    version = 1,
    exportSchema = false
)
abstract class MyDatabase : RoomDatabase() {

    abstract val favoriteDao : FavoriteDao

    companion object{
        @Volatile
        private var instanceDb: MyDatabase? = null
        fun getDatabase(context: Context): MyDatabase {
            val tempInstance = instanceDb
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyDatabase::class.java,
                    "jetpackpro.db"
                ).build()
                instanceDb = instance
                return instance
            }
        }
    }
}