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
import com.surya.androidjetpackpro.ui.tvshow.TvShowPagedListAdapter

/**
 * A simple [Fragment] subclass.
 */
class FavoriteTVShowFragment : Fragment() {


    private lateinit var vm: FavoriteTvShowViewModel

    private lateinit var shimmer: ShimmerFrameLayout
    private lateinit var recyclerView: RecyclerView

    companion object {
        fun newInstance(): FavoriteTVShowFragment {
            return FavoriteTVShowFragment()
        }
    }

    private val adapterList: TvShowPagedListAdapter by lazy {
        TvShowPagedListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite_tvshow, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        shimmer = view.findViewById(R.id.shimmer)
        recyclerView = view.findViewById(R.id.rv_tv_show)

        recyclerView.layoutManager = GridLayoutManager(context,2)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapterList

        vm = ViewModelProviders.of(this, Injection.provideViewModelFactory(requireContext()) )
            .get(FavoriteTvShowViewModel::class.java)

        vm.favTvShow.observe(this, Observer {
            adapterList.submitList(it)
            shimmer.visibility = View.GONE
        })

    }
}
