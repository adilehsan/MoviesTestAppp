package com.ideologer.moviestestappp.dto.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class ResultMovies : Serializable {
    @SerializedName("adult")
    @Expose
    var adult: Boolean? = null

    @SerializedName("backdrop_path")
    @Expose
    var backdropPath: String? = null

    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("media_type")
    @Expose
    var mediaType: String? = null

    @SerializedName("original_language")
    @Expose
    var originalLanguage: String? = null

    @SerializedName("original_title")
    @Expose
    var originalTitle: String? = null

    @SerializedName("overview")
    @Expose
    var overview: String? = null

    @SerializedName("popularity")
    @Expose
    var popularity: Double? = null

    @SerializedName("poster_path")
    @Expose
    var posterPath: String? = null

    @SerializedName("release_date")
    @Expose
    var releaseDate: String? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("video")
    @Expose
    var video: Boolean? = null

    @SerializedName("vote_average")
    @Expose
    var voteAverage: Double? = null

    @SerializedName("vote_count")
    @Expose
    var voteCount: Int? = null

    fun ifCurrentYear(): Boolean {
        var dateRelease = ""
        val formatter = SimpleDateFormat("yyyy")
        val date = Date()
        var currentDate = formatter.format(date)
        val format = SimpleDateFormat("yyyy-MM-dd")
        try {
            val dateYear = format.parse(releaseDate)
            dateRelease = formatter.format(dateYear)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        if (currentDate.toString().equals(dateRelease))
            return true
        return false
    }

}
