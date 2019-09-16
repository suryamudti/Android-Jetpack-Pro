package com.surya.androidjetpackpro.ui.tvshow

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.facebook.shimmer.ShimmerFrameLayout
import com.surya.androidjetpackpro.R
import com.surya.androidjetpackpro.data.models.TVShow
import com.surya.androidjetpackpro.utils.Constants
import com.surya.androidjetpackpro.utils.EspressoIdlingResource
import com.surya.androidjetpackpro.utils.toast
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class TVShowFragment : Fragment(), KodeinAware, TVShowListener {


    override val kodein by kodein()

    private val factory : TvshowViewModelFactory by instance()

    private lateinit var viewModel: TvshowViewModel

    private lateinit var shimmer: ShimmerFrameLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.tvshow_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {        
        super.onViewCreated(view, savedInstanceState)
        shimmer = view.findViewById(R.id.shimmer)
        recyclerView = view.findViewById(R.id.rv_tv_show)
        recyclerView.layoutManager = GridLayoutManager(context,2)
        recyclerView.setHasFixedSize(true)

        swipeRefreshLayout = view.findViewById(R.id.swipeRefresh)
        swipeRefreshLayout.setOnRefreshListener {
            viewModel.getTVShows(Constants.REFRESH)
        }
        
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this, factory).get(TvshowViewModel::class.java)
        viewModel.getTVShows(Constants.DEFAULT).observe(this, Observer {

            if (it != null){
                shimmer.visibility = View.GONE
                val mAdapter = GroupAdapter<ViewHolder>().apply {
                    addAll(it.toItem())
                }
                recyclerView.adapter = mAdapter
            }
        })

        viewModel.tvShowListener = this
        shimmer.visibility = View.VISIBLE
    }

    override fun onStarted() {
        shimmer.visibility = View.VISIBLE
        shimmer.startShimmerAnimation()
    }

    override fun onSuccess(data: List<TVShow>) {
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

    override fun onFailure(message: String) {
        activity?.toast(message)
    }

    private fun List<TVShow>.toItem() : List<TVShowItem>{
        return this.map {
            TVShowItem(it)
        }
    }

}
