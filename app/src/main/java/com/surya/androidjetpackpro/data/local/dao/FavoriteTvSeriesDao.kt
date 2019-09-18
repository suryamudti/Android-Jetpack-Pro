package com.surya.androidjetpackpro.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.surya.androidjetpackpro.data.models.TVShow

/**
 * Created by suryamudti on 18/09/2019.
 */

@Dao
interface FavoriteTvSeriesDao {

    @Insert
    suspend fun insertTVShow( tvShow: TVShow)

    @Query("SELECT * FROM  TVShow")
    fun getAllTVShows() : LiveData<List<TVShow>>

    @Query("SELECT * FROM TVShow WHERE id = :id")
    fun getSingleTVShow(id : Int) : LiveData<TVShow>

    @Delete
    fun deleteTVShows(tvShow: TVShow)
}