package com.surya.androidjetpackpro.ui.tvshow.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.surya.androidjetpackpro.R
import com.surya.androidjetpackpro.data.models.TVShow
import com.surya.androidjetpackpro.di.Injection
import com.surya.androidjetpackpro.ui.favorite.FavoriteMovieViewModel
import com.surya.androidjetpackpro.ui.favorite.FavoriteTvShowViewModel
import com.surya.androidjetpackpro.utils.Constants
import com.surya.androidjetpackpro.utils.toast
import kotlinx.android.synthetic.main.activity_tv_show_detail.*

class TvShowDetailActivity : AppCompatActivity() {

    private lateinit var tvShow: TVShow

    private var isFavorite = false

    private lateinit var vm : FavoriteTvShowViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tv_show_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        tvShow = intent.getParcelableExtra<TVShow>(Constants.TV_SHOW_ID_EXTRAS)

        tv_release_date_title_detail.text = getString(R.string.release_date)
        tv_score_title.text = getString(R.string.user_score)
        tv_description_title_detail.text = getString(R.string.overview)

        tv_title_detail.text = tvShow.name
        tv_release_date_detail.text = tvShow.first_air_date
        tv_score_value_detail.text = tvShow.vote_average
        tv_description_detail.text = tvShow.overview

        Glide.with(applicationContext).load("https://image.tmdb.org/t/p/w185" + tvShow.poster_path)
            .into(img_poster_detail)

        supportActionBar?.title = tvShow.name

        vm = ViewModelProviders.of(this, Injection.provideViewModelFactory(this))
            .get(FavoriteTvShowViewModel::class.java)

        vm.getSingle(tvShow.id).observe(this, Observer {
            if (it != null){
                isFavorite =true
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                finish()
                return true
            }
            R.id.menu_add_to_favorite -> {
                if (isFavorite){
                    vm.deleteFromFavorite(tvShow)
                    isFavorite = false
                    toast("deleted")
                    invalidateOptionsMenu()
                }else{
                    vm.addToFavorite(tvShow)
                    isFavorite = true
                    toast("added")
                    invalidateOptionsMenu()
                }

                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menu?.getItem(0)?.isVisible = true

        if (isFavorite){
            menu?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_white_24dp)
        }else{
            menu?.getItem(0)?.icon = ContextCompat
                .getDrawable(this, R.drawable.ic_favorite_border_white_24dp)
        }
        return super.onPrepareOptionsMenu(menu)
    }
}
