package com.surya.androidjetpackpro.ui.favorite


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.surya.mademovieapp.ui.movie.MovieItem
import com.facebook.shimmer.ShimmerFrameLayout
import com.surya.androidjetpackpro.R
import com.surya.androidjetpackpro.data.models.Movie
import com.surya.androidjetpackpro.ui.movie.MovieViewModel
import com.surya.androidjetpackpro.ui.movie.MovieViewModelFactory
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

/**
 * A simple [Fragment] subclass.
 */
class FavoriteMovieFragment : Fragment(), KodeinAware {

    override val kodein by kodein()

    private val factory : MovieViewModelFactory by instance()

    private lateinit var mainViewModel: MovieViewModel

    private lateinit var shimmer: ShimmerFrameLayout
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        shimmer = view.findViewById(R.id.shimmer)
        recyclerView = view.findViewById(R.id.rv_movie)

        recyclerView.layoutManager = GridLayoutManager(context,2)
        recyclerView.setHasFixedSize(true)

        mainViewModel = ViewModelProviders.of(this, factory).get(MovieViewModel::class.java)

        mainViewModel.getLocalMovie().observe(this, Observer {

            if (it != null){
                shimmer.visibility = View.GONE
                val mAdapter = GroupAdapter<ViewHolder>().apply {
                    addAll(it.toItem())
                }
                recyclerView.adapter = mAdapter
            }
        })

        shimmer.visibility = View.VISIBLE
    }

    private fun List<Movie>.toItem() : List<MovieItem>{
        return this.map {
            MovieItem(it)
        }
    }

}
