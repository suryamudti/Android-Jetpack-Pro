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
import com.surya.androidjetpackpro.data.models.TVShow
import com.surya.androidjetpackpro.ui.tvshow.TVShowItem
import com.surya.androidjetpackpro.ui.tvshow.TvshowViewModel
import com.surya.androidjetpackpro.ui.tvshow.TvshowViewModelFactory
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

/**
 * A simple [Fragment] subclass.
 */
class FavoriteTVShowFragment : Fragment(), KodeinAware {

    override val kodein by kodein()

    private val factory : TvshowViewModelFactory by instance()

    private lateinit var viewModel: TvshowViewModel

    private lateinit var shimmer: ShimmerFrameLayout
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val viewRoot = inflater.inflate(R.layout.fragment_favorite_tvshow, container, false)

        shimmer = viewRoot.findViewById(R.id.shimmer)
        recyclerView = viewRoot.findViewById(R.id.rv_tv_show)

        recyclerView.layoutManager = GridLayoutManager(context,2)
        recyclerView.setHasFixedSize(true)

        return viewRoot
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory).get(TvshowViewModel::class.java)

        viewModel.getLocalTVShow().observe(this, Observer {
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

    private fun List<TVShow>.toItem() : List<TVShowItem>{
        return this.map {
            TVShowItem(it)
        }
    }
}
