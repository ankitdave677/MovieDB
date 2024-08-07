package com.example.moviedb;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OMDBApiClient {
    private static final String BASE_URL = "http://www.omdbapi.com/";
    private static OMDBApiClient instance;
    private OMDBApi omdbApi;

    private OMDBApiClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        omdbApi = retrofit.create(OMDBApi.class);
    }

    public static synchronized OMDBApiClient getInstance() {
        if (instance == null) {
            instance = new OMDBApiClient();
        }
        return instance;
    }

    public OMDBApi getOMDBApi() {
        return omdbApi;
    }
}
