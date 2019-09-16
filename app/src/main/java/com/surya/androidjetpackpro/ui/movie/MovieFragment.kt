package com.surya.androidjetpackpro.ui.movie

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.surya.androidjetpackpro.data.models.Movie
import com.dicoding.surya.mademovieapp.ui.movie.MovieItem
import com.dicoding.surya.mademovieapp.ui.movie.MovieListener
import com.facebook.shimmer.ShimmerFrameLayout
import com.surya.androidjetpackpro.R
import com.surya.androidjetpackpro.utils.Constants
import com.surya.androidjetpackpro.utils.EspressoIdlingResource
import com.surya.androidjetpackpro.utils.toast
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class MovieFragment : Fragment(), MovieListener, KodeinAware {

    override val kodein by kodein()

    private val factory : MovieViewModelFactory by instance()

    private lateinit var viewModel: MovieViewModel

    private lateinit var shimmer: ShimmerFrameLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movie_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        shimmer = view.findViewById(R.id.shimmer)
        recyclerView = view.findViewById(R.id.rv_movie)

        recyclerView.layoutManager = GridLayoutManager(context,2)
        recyclerView.setHasFixedSize(true)

        swipeRefreshLayout = view.findViewById(R.id.swipeRefresh)

        swipeRefreshLayout.setOnRefreshListener {
            viewModel.getMovies(Constants.REFRESH)
        }

        shimmer.startShimmerAnimation()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (activity != null){
            viewModel = ViewModelProviders.of(this, factory).get(MovieViewModel::class.java)
            viewModel.getMovies(Constants.DEFAULT).observe(this, Observer {

                if (it != null){
                    this.onSuccess(it)
                }
            })

            viewModel.movieListener = this

        }


    }

    override fun onStarted() {
        shimmer.visibility = View.VISIBLE
        shimmer.startShimmerAnimation()
        recyclerView.visibility = View.GONE
    }

    override fun onFailure(message: String) {
        shimmer.visibility = View.GONE
        activity?.toast(message)
    }

    override fun onSuccess(data: List<Movie>) {

        recyclerView.visibility = View.VISIBLE
        shimmer.visibility = View.GONE
        swipeRefreshLayout.isRefreshing = false
        val mAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(data.toItem())
        }
        recyclerView.adapter = mAdapter
        if (!EspressoIdlingResource.idlingresource.isIdleNow) {
            //Memberitahukan bahwa tugas sudah selesai dijalankan
            EspressoIdlingResource.decrement()
        }
    }

    private fun List<Movie>.toItem() : List<MovieItem>{
        return this.map {
            MovieItem(it)
        }
    }
}
