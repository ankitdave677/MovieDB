package com.example.moviedb;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class MovieDetailsActivity extends AppCompatActivity {

    private TextView titleTextView;
    private TextView yearTextView;
    private TextView studioTextView;
    private TextView ratingTextView;
    private TextView descriptionTextView;
    private ImageView posterImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        // Enable the Up button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Initialize views
        titleTextView = findViewById(R.id.movie_title);
        yearTextView = findViewById(R.id.movie_year);
        studioTextView = findViewById(R.id.movie_studio);
        ratingTextView = findViewById(R.id.movie_rating);
        descriptionTextView = findViewById(R.id.movie_description);
        posterImageView = findViewById(R.id.movie_poster);

        // Get the movie from the intent
        Movie movie = getIntent().getParcelableExtra("movie");
        if (movie != null) {
            titleTextView.setText(movie.getTitle());
            yearTextView.setText(movie.getYear());
            studioTextView.setText(movie.getStudio());
            ratingTextView.setText(movie.getRating());
            descriptionTextView.setText(movie.getDescription());
            Glide.with(this).load(movie.getPoster()).into(posterImageView);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle the Up button click
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
