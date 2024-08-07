package com.example.moviedb;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText searchEditText;
    private Button searchButton;
    private RecyclerView moviesRecyclerView;
    private MovieAdapter movieAdapter;
    private OMDBApiClient omdbApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchEditText = findViewById(R.id.searchEditText);
        searchButton = findViewById(R.id.searchButton);
        moviesRecyclerView = findViewById(R.id.moviesRecyclerView);

        moviesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        movieAdapter = new MovieAdapter();
        moviesRecyclerView.setAdapter(movieAdapter);

        omdbApiClient = OMDBApiClient.getInstance();

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = searchEditText.getText().toString().trim();
                if (!query.isEmpty()) {
                    searchMovies(query);
                } else {
                    Toast.makeText(MainActivity.this, "Please enter a movie name", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void searchMovies(String query) {
        Call<OMDBResponse> call = omdbApiClient.getOMDBApi().searchMovies(query, "eb46f94");
        call.enqueue(new Callback<OMDBResponse>() {
            @Override
            public void onResponse(Call<OMDBResponse> call, Response<OMDBResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Movie> movies = response.body().getSearch();
                    movieAdapter.setMovies(movies);
                } else {
                    Toast.makeText(MainActivity.this, "No movies found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<OMDBResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
