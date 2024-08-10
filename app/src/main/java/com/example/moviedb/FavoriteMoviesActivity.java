package com.example.moviedb;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class FavoriteMoviesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MovieAdapter adapter;
    private FavoritesDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_movies);

        // Enable back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dbHelper = new FavoritesDatabaseHelper(this);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadFavoriteMovies();
    }

    private void loadFavoriteMovies() {
        List<Movie> favoriteMovies = dbHelper.getAllFavorites();
        if (favoriteMovies.isEmpty()) {
            Toast.makeText(this, "No favorite movies found", Toast.LENGTH_SHORT).show();
        } else {
            adapter = new MovieAdapter(favoriteMovies, movie -> {
                // Do nothing on item click for favorite movies
            }, dbHelper);
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Navigate back to the previous activity
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
