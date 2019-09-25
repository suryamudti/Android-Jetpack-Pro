package com.surya.androidjetpackpro.utils

/**
 * Created by suryamudti on 25/09/2019.
 */

data class Resource<out T>(val status: Status, val data: T?, val message: String?) {

    companion object {
        fun <T> success(data: T?): Resource<T> = Resource(Status.SUCCESS, data, null)

        fun <T> error(msg: String, data: T? = null): Resource<T> = Resource(Status.ERROR, data, msg)

        fun <T> loading(data: T?): Resource<T> = Resource(Status.LOADING, data, null)
    }
}

enum class Status { SUCCESS, ERROR, LOADING }