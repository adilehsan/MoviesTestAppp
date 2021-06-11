package com.ideologer.moviestestappp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.ideologer.moviestestappp.utils.Constant
import com.ideologer.moviestestappp.R
import com.ideologer.moviestestappp.databinding.FragmentMoviesDetailBinding
import com.ideologer.moviestestappp.dto.response.ResultMovies
import com.ideologer.moviestestappp.viewModel.MoviesDetailViewModel

class MoviesDetailFragment : Fragment() {
    var binding: FragmentMoviesDetailBinding? = null
    lateinit var viewModel: MoviesDetailViewModel
    var movieItem: ResultMovies? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMoviesDetailBinding.inflate(inflater, container, false)
        viewModel = ViewModelProviders.of(this).get(MoviesDetailViewModel::class.java)
        binding?.lifecycleOwner = this.viewLifecycleOwner
        binding?.viewModel = viewModel
        inIT()
        return binding?.root
    }

    private fun inIT() {
        arguments?.let {
            movieItem = it.getSerializable(MoviesListFragment().MOVIES_ITEM) as ResultMovies
            movieItem?.let { itMovie ->
                viewModel.imagePoster.value = itMovie.backdropPath
                viewModel.title.value = itMovie.title
                viewModel.overView.value = itMovie.overview
                viewModel.voteAverage.value = itMovie.voteAverage
                viewModel.voteCount.value = itMovie.voteCount
                Glide.with(requireContext())
                    .load(Constant.IMAGE_BASE_URL.plus(viewModel.imagePoster.value.toString()))
                    .error(R.drawable.logo_movies)
                    .placeholder(R.drawable.logo_movies)
                    .into(binding?.ivBackDoor!!)
            }
        }
    }

    fun getInstance(bundle: Bundle?): MoviesDetailFragment? {
        val fragment = MoviesDetailFragment()
        if (bundle != null) fragment.arguments = bundle
        return fragment
    }
}