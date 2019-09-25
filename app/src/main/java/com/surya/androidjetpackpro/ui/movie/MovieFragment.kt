package com.surya.androidjetpackpro.ui.movie

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
import com.surya.androidjetpackpro.databinding.MovieFragmentBinding
import com.surya.androidjetpackpro.di.Injection

class MovieFragment : Fragment() {

    companion object {
        fun newInstance(): MovieFragment {
            return MovieFragment()
        }
    }

    private val adapterList: MoviesPagedListAdapter by lazy {
        MoviesPagedListAdapter()
    }

    private lateinit var viewModel: MovieViewModel
    private lateinit var binding : MovieFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        retainInstance = true
        binding = DataBindingUtil.inflate(inflater,R.layout.movie_fragment,container,false)
        binding.lifecycleOwner = this
        viewModel = ViewModelProviders.of(this,Injection.provideViewModelFactory(requireContext()))
            .get(MovieViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.shimmer.visibility = View.VISIBLE
        binding.shimmer.startShimmerAnimation()

        binding.rvMovie.adapter = adapterList
        binding.rvMovie.layoutManager = GridLayoutManager(context,2)
        binding.rvMovie.setHasFixedSize(true)

        viewModel.getMovies().observe(this, Observer {
            adapterList.submitList(it)
            binding.rvMovie.visibility = View.VISIBLE
            binding.shimmer.visibility = View.GONE
        })
    }
}
