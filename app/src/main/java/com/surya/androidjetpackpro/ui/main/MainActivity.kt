package com.surya.androidjetpackpro.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.surya.androidjetpackpro.R
import com.surya.androidjetpackpro.ui.favorite.FavoriteFragment
import com.surya.androidjetpackpro.ui.movie.MovieFragment
import com.surya.androidjetpackpro.ui.tvshow.TVShowFragment

class MainActivity : AppCompatActivity() {

    private val SELECTED_MENU = "selected_menu"
    private var navView: BottomNavigationView? = null

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.action_movie -> {
                switchFragment(MovieFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.action_tvshow -> {
                switchFragment(TVShowFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.action_favorite -> {
                switchFragment(FavoriteFragment())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun switchFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .replace(R.id.container, fragment)
            .commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navView = findViewById(R.id.navigation)
        navView!!.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        switchFragment(MovieFragment())
        savedInstanceState?.getInt(SELECTED_MENU) ?: (navView!!.selectedItemId == R.id.action_movie)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(SELECTED_MENU, navView!!.selectedItemId)
    }
}
