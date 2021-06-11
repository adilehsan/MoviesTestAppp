package com.ideologer.moviestestappp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MoviesDetailViewModel : ViewModel() {
    var imagePoster = MutableLiveData<String>()
    var title = MutableLiveData<String>()
    var overView = MutableLiveData<String>()
    var voteCount = MutableLiveData<Int>()
    var voteAverage = MutableLiveData<Double>()
}