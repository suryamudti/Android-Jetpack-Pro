package com.surya.androidjetpackpro.ui.tvshow

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.facebook.shimmer.ShimmerFrameLayout
import com.surya.androidjetpackpro.R
import com.surya.androidjetpackpro.data.models.TVShow
import com.surya.androidjetpackpro.databinding.TvshowFragmentBinding
import com.surya.androidjetpackpro.di.Injection
import com.surya.androidjetpackpro.ui.movie.MovieViewModel
import com.surya.androidjetpackpro.ui.movie.MoviesPagedListAdapter
import com.surya.androidjetpackpro.utils.Constants
import com.surya.androidjetpackpro.utils.EspressoIdlingResource
import com.surya.androidjetpackpro.utils.toast
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class TVShowFragment : Fragment() {


    companion object {
        fun newInstance(): TVShowFragment {
            return TVShowFragment()
        }
    }

    private val adapterList: TvShowPagedListAdapter by lazy {
        TvShowPagedListAdapter()
    }
    private lateinit var viewModel: TvshowViewModel
    private lateinit var binder: TvshowFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        retainInstance = true
        binder = DataBindingUtil.inflate(inflater,R.layout.tvshow_fragment,container,false)
        binder.lifecycleOwner = this
        viewModel = ViewModelProviders.of(this, Injection.provideViewModelFactory(requireContext()))
            .get(TvshowViewModel::class.java)
        return binder.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {        
        super.onViewCreated(view, savedInstanceState)
        binder.shimmer.visibility = View.VISIBLE
        binder.shimmer.startShimmerAnimation()

        binder.rvTvShow.adapter = adapterList
        binder.rvTvShow.layoutManager = GridLayoutManager(context,2)
        binder.rvTvShow.setHasFixedSize(true)


        viewModel.tvShowList.observe(this, Observer {
            adapterList.submitList(it)
            if (it != null){
                binder.rvTvShow.visibility = View.VISIBLE
                binder.shimmer.visibility = View.GONE
            }
        })
        
    }


}
