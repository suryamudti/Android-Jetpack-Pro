package com.surya.androidjetpackpro.ui.tvshow

import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import com.surya.androidjetpackpro.R
import com.surya.androidjetpackpro.testing.SingleFragmentActivity
import com.surya.androidjetpackpro.utils.EspressoIdlingResource
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by suryamudti on 16/09/2019.
 */
class TVShowFragmentTest {

    @Rule
    @JvmField
    val activityRule: ActivityTestRule<SingleFragmentActivity>
            = ActivityTestRule(SingleFragmentActivity::class.java)

    private val tvShowFragment = TVShowFragment()

    @Before
    fun setUp(){
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingresource)
        activityRule.activity.setFragment(tvShowFragment)
    }

    @After
    fun tearDown(){
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingresource)
    }

    @Test
    fun getTVShow(){
        Espresso.onView(ViewMatchers.withId(R.id.rv_tv_show))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.rv_tv_show))
            .perform(RecyclerViewActions.actionOnItemAtPosition<ViewHolder>(0, ViewActions.click()))

        Espresso.onView(ViewMatchers.withId(R.id.menu_add_to_favorite)).perform(ViewActions.click())
    }
}