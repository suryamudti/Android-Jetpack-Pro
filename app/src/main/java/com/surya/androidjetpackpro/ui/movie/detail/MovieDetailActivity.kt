package com.surya.androidjetpackpro.ui.movie.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.surya.androidjetpackpro.R
import com.surya.androidjetpackpro.data.models.Movie
import com.surya.androidjetpackpro.utils.Constants
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var movie : Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        movie = intent.getParcelableExtra<Movie>(Constants.MOVIE_ID_EXTRAS)

        tv_release_date_title_detail.text = getString(R.string.release_date)
        tv_score_title.text = getString(R.string.user_score)
        tv_description_title_detail.text = getString(R.string.overview)

        tv_title_detail.text = movie.title
        tv_release_date_detail.text = movie.release_date
        tv_score_value_detail.text = movie.vote_average
        tv_description_detail.text = movie.overview

        Glide.with(this).load("https://image.tmdb.org/t/p/w185" + movie.poster_path)
            .into(img_poster_detail)

        supportActionBar?.title = movie.title
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            android.R.id.home ->{
                finish()
                return true
            }

        }

        return super.onOptionsItemSelected(item)
    }
}
