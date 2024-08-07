package com.example.moviedb;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OMDBApi {
    @GET("/")
    Call<OMDBResponse> searchMovies(@Query("s") String query, @Query("apikey") String apiKey);
}
