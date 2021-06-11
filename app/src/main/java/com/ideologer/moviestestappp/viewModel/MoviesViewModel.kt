package com.ideologer.moviestestappp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ideologer.moviestestappp.dto.response.MoviesResponse
import com.ideologer.moviestestappp.network.ApiServices
import com.ideologer.moviestestappp.network.RestClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesViewModel : ViewModel() {
    var moviesLiveData: MutableLiveData<MoviesResponse>? = null

    private var apiService: ApiServices? = null

    val extraCashResponse: LiveData<MoviesResponse>?
        get() = moviesLiveData

    fun inIt() {
        apiService = RestClient.apiService
        moviesLiveData = MutableLiveData()
        getMovies()
    }

    fun getMovies() {
        apiService?.getMoviesList("83d01f18538cb7a275147492f84c3698")
            ?.enqueue(object : Callback<MoviesResponse> {
                override fun onResponse(
                    call: Call<MoviesResponse>,
                    response: Response<MoviesResponse>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        moviesLiveData?.postValue(response.body())
                    } else {
                        moviesLiveData?.postValue(null)
                    }
                }

                override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                    moviesLiveData?.value = null
                }
            })
    }
}