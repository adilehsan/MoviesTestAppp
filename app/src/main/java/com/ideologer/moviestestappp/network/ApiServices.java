package com.ideologer.moviestestappp.network;

import com.ideologer.moviestestappp.dto.response.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServices {
    @GET("4/list/1?")
    Call<MoviesResponse> getMoviesList(@Query("api_key") String apiKey);
}
