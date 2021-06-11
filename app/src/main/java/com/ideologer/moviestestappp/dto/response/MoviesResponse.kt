package com.ideologer.moviestestappp.dto.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MoviesResponse(@SerializedName("results") var moviesList: ArrayList<ResultMovies>?) : Parcelable
