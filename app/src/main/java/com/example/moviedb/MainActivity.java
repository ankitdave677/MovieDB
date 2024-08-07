package com.example.moviedb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText searchEditText;
    private Button searchButton;
    private RecyclerView recyclerView;
    private MovieAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchEditText = findViewById(R.id.search_edit_text);
        searchButton = findViewById(R.id.search_button);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = searchEditText.getText().toString().trim();
                if (!query.isEmpty()) {
                    OMDBApiClient.fetchMovies(MainActivity.this, query, new OMDBApiClient.MovieFetchListener() {
                        @Override
                        public void onMoviesFetched(List<Movie> movies) {
                            adapter = new MovieAdapter(movies, new MovieAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(Movie movie) {
                                    Intent intent = new Intent(MainActivity.this, MovieDetailsActivity.class);
                                    intent.putExtra("movie", movie);
                                    startActivity(intent);
                                }
                            });
                            recyclerView.setAdapter(adapter);
                        }

                        @Override
                        public void onError(String error) {
                            Toast.makeText(MainActivity.this, "Error: " + error, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}
