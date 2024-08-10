package com.example.moviedb;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Variable declaration
    private EditText searchEditText;
    private Button searchButton;
    private Button favoritesButton;
    private RecyclerView recyclerView;
    private MovieAdapter adapter;
    private FavoritesDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new FavoritesDatabaseHelper(this);

        searchEditText = findViewById(R.id.search_edit_text);
        searchButton = findViewById(R.id.search_button);
        favoritesButton = findViewById(R.id.favorites_button);
        recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Set up the search button to fetch movies based on the user's query
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = searchEditText.getText().toString();
                OMDBApiClient.fetchMovies(MainActivity.this, query, new OMDBApiClient.MovieFetchListener() {
                    @Override
                    public void onMoviesFetched(List<Movie> movies) {
                        adapter = new MovieAdapter(movies, new MovieAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(Movie movie) {
                                Intent intent = new Intent(MainActivity.this, MovieDetailsActivity.class);
                                intent.putExtra("movie", (Parcelable) movie);                                startActivity(intent);
                            }
                        }, dbHelper);
                        recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onError(String error) {
                        // Handle error
                    }
                });
            }
        });

        // Set up the favorites button to open the favorites screen
        favoritesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FavoriteMoviesActivity.class);
                startActivity(intent);
            }
        });
    }
}
