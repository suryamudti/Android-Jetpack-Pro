package com.surya.androidjetpackpro.utils

import androidx.test.espresso.IdlingResource
import androidx.test.espresso.idling.CountingIdlingResource



/**
 * Created by suryamudti on 16/09/2019.
 */
object EspressoIdlingResource {
    private val RESOURCE = "GLOBAL"
    private val countingIdlingResource = CountingIdlingResource(RESOURCE)

    val idlingresource: IdlingResource
        get() = countingIdlingResource

    fun increment() {
        countingIdlingResource.increment()
    }

    fun decrement() {
        countingIdlingResource.decrement()
    }
}