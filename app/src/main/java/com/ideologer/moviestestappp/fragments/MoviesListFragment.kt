package com.ideologer.moviestestappp.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.ideologer.moviestestappp.`interface`.IFragmentListner
import com.ideologer.moviestestappp.`interface`.MovieItemClick
import com.ideologer.moviestestappp.adapter.MoviesListAdapter
import com.ideologer.moviestestappp.databinding.FragmentMoviesListBinding
import com.ideologer.moviestestappp.dto.response.MoviesResponse
import com.ideologer.moviestestappp.dto.response.ResultMovies
import com.ideologer.moviestestappp.utils.Constant
import com.ideologer.moviestestappp.utils.ProgressDialog
import com.ideologer.moviestestappp.viewModel.MoviesViewModel

class MoviesListFragment : Fragment(), MovieItemClick {
    lateinit var vmMoviesList: MoviesViewModel
    var moviesListAdapter: MoviesListAdapter? = null
    private var fragmentListener: IFragmentListner? = null
    var fragmentMoviesListBinding: FragmentMoviesListBinding? = null
    lateinit var progressDialog: ProgressDialog
    val MOVIES_ITEM = "movies-item"
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentMoviesListBinding = FragmentMoviesListBinding.inflate(inflater, container, false)
        return fragmentMoviesListBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inIT()
    }

    fun inIT() {
        vmMoviesList = ViewModelProviders.of(this).get(MoviesViewModel::class.java)
        progressDialog = ProgressDialog(requireContext())
        progressDialog.setMessage("Loading...")
        progressDialog.setCanceledOnTouchOutside(false)
        progressDialog.setCancelable(false)
        fragmentMoviesListBinding?.rvMovies?.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        vmMoviesList.inIt()
        fragmentMoviesListBinding?.sv?.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                moviesListAdapter?.let {
                    it.filter.filter(newText)
                }
                return false
            }
        })
        progressDialog.show()
        vmMoviesList.extraCashResponse?.observe(viewLifecycleOwner, Observer<MoviesResponse> {
            progressDialog.cancel()
            it?.moviesList?.let { itList ->
                moviesListAdapter = MoviesListAdapter(itList, requireContext(), this)
                fragmentMoviesListBinding?.rvMovies?.adapter = moviesListAdapter
            } ?: kotlin.run {
                Toast.makeText(requireContext(),"Error in getting movies",Toast.LENGTH_LONG).show()
            }

        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentListener = try {
            context as IFragmentListner
        } catch (classCastException: ClassCastException) {
            throw IllegalAccessError("Activity MUST implement IFragmentListener")
        }

    }

    override fun itemClick(resultMovies: ResultMovies) {
        val bundle = Bundle()
        bundle.putSerializable(MOVIES_ITEM, resultMovies)
        fragmentListener?.addFragment(
            MoviesDetailFragment().getInstance(bundle),
            true,
            true,
            Constant.DETAIL_SCREEN_TAG
        )

    }


    fun getInstance(bundle: Bundle?): MoviesListFragment? {
        val fragment = MoviesListFragment()
        if (bundle != null) fragment.arguments = bundle
        return fragment
    }

}