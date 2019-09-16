package com.surya.androidjetpackpro.ui.movies

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.surya.androidjetpackpro.R
import com.surya.androidjetpackpro.testing.SingleFragmentActivity
import com.surya.androidjetpackpro.ui.movie.MovieFragment
import com.surya.androidjetpackpro.utils.EspressoIdlingResource
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.movie_fragment.view.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by suryamudti on 16/09/2019.
 */
class MovieFragmentTest {
    @Rule
    @JvmField
    val activityRule: ActivityTestRule<SingleFragmentActivity>
            = ActivityTestRule(SingleFragmentActivity::class.java)
    private val moviesFragment = MovieFragment()

    @Before
    fun setUp(){
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingresource)
        activityRule.activity.setFragment(moviesFragment)
    }

    @After
    fun tearDown(){
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingresource)
    }

    @Test
    fun getMoviesData(){
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<ViewHolder>(0, click()))
    }

}