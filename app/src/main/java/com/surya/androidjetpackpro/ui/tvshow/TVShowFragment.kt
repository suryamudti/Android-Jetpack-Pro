package com.surya.androidjetpackpro.ui.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.surya.androidjetpackpro.R
import com.surya.androidjetpackpro.databinding.TvshowFragmentBinding
import com.surya.androidjetpackpro.di.Injection

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


        viewModel.getTvShow().observe(this, Observer {
            adapterList.submitList(it)
            if (it != null){
                binder.rvTvShow.visibility = View.VISIBLE
                binder.shimmer.visibility = View.GONE
            }
        })
        
    }


}
