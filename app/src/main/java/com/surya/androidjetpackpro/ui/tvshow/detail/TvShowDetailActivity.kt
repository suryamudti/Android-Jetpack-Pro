package com.surya.androidjetpackpro.ui.tvshow.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.surya.androidjetpackpro.R
import com.surya.androidjetpackpro.data.models.TVShow
import com.surya.androidjetpackpro.utils.Constants
import kotlinx.android.synthetic.main.activity_tv_show_detail.*

class TvShowDetailActivity : AppCompatActivity() {

    private lateinit var tvShow: TVShow

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
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
