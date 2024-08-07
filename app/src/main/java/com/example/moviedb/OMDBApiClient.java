package com.example.moviedb;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OMDBApiClient {

    private static final String API_KEY = "eb46f94";
    private static final String BASE_URL = "http://www.omdbapi.com/?apikey=" + API_KEY + "&s=";

    public interface MovieFetchListener {
        void onMoviesFetched(List<Movie> movies);
        void onError(String error);
    }

    public static void fetchMovies(Context context, String query, final MovieFetchListener listener) {
        String url = BASE_URL + query;

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray moviesArray = response.getJSONArray("Search");
                    List<Movie> movies = new ArrayList<>();
                    for (int i = 0; i < moviesArray.length(); i++) {
                        JSONObject movieObject = moviesArray.getJSONObject(i);
                        Movie movie = new Movie();
                        movie.setTitle(movieObject.getString("Title"));
                        movie.setYear(movieObject.getString("Year"));
                        movie.setPoster(movieObject.getString("Poster"));
                        movies.add(movie);
                    }
                    listener.onMoviesFetched(movies);
                } catch (JSONException e) {
                    listener.onError("Parsing error");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(error.getMessage());
            }
        });

        requestQueue.add(request);
    }
}
