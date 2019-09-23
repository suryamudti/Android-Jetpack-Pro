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
import com.facebook.shimmer.ShimmerFrameLayout
import com.surya.androidjetpackpro.R
import com.surya.androidjetpackpro.di.Injection
import com.surya.androidjetpackpro.ui.movie.MoviesPagedListAdapter

/**
 * A simple [Fragment] subclass.
 */
class FavoriteMovieFragment : Fragment(){

    companion object {
        fun newInstance(): FavoriteMovieFragment {
            return FavoriteMovieFragment()
        }
    }

    private val adapterList: MoviesPagedListAdapter by lazy {
        MoviesPagedListAdapter()
    }

    private lateinit var vm: FavoriteMovieViewModel

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
        shimmer.startShimmerAnimation()


        recyclerView.layoutManager = GridLayoutManager(context,2)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapterList

        vm = ViewModelProviders.of(this, Injection.provideViewModelFactory(requireContext()) )
            .get(FavoriteMovieViewModel::class.java)

        vm.favMovies.observe(this, Observer {
            adapterList.submitList(it)
            shimmer.visibility = View.GONE
        })

    }



}
