package com.surya.androidjetpackpro.ui.movie.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.surya.androidjetpackpro.R
import com.surya.androidjetpackpro.data.models.Movie
import com.surya.androidjetpackpro.di.Injection
import com.surya.androidjetpackpro.ui.favorite.FavoriteMovieViewModel
import com.surya.androidjetpackpro.utils.Constants
import com.surya.androidjetpackpro.utils.toast
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var movie : Movie

    private var isFavorite = false

    private lateinit var vm : FavoriteMovieViewModel

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

        vm = ViewModelProviders.of(this, Injection.provideViewModelFactory(this))
            .get(FavoriteMovieViewModel::class.java)

        vm.getSingle(movie.id).observe(this, Observer {
            if (it != null){
                isFavorite =true
            }
        })

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            android.R.id.home ->{
                finish()
                return true
            }
            R.id.menu_add_to_favorite -> {
                if (isFavorite){
                    vm.deleteFromFavorite(movie)
                    isFavorite = false
                    toast("deleted")
                    invalidateOptionsMenu()
                }else{
                    vm.addToFavorite(movie)
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
