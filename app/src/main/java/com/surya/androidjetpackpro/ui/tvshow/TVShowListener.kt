package com.surya.androidjetpackpro.ui.tvshow

import com.surya.androidjetpackpro.data.models.TVShow

/**
 * Created by suryamudti on 10/09/2019.
 */
interface TVShowListener {

    fun onStarted()
    fun onSuccess(data: List<TVShow>)
    fun onFailure(message: String)
}