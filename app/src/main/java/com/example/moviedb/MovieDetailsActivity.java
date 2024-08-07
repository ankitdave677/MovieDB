package com.example.moviedb;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class MovieDetailsActivity extends AppCompatActivity {

    private ImageView moviePosterImageView;
    private TextView titleTextView;
    private TextView studioTextView;
    private TextView ratingTextView;
    private TextView yearTextView;
    private TextView descriptionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        moviePosterImageView = findViewById(R.id.poster_image_view);
        titleTextView = findViewById(R.id.title_text_view);
        studioTextView = findViewById(R.id.studio_text_view);
        ratingTextView = findViewById(R.id.rating_text_view);
        yearTextView = findViewById(R.id.year_text_view);
        descriptionTextView = findViewById(R.id.description_text_view);

        Movie movie = getIntent().getParcelableExtra("movie");

        if (movie != null) {
            titleTextView.setText(movie.getTitle());
            studioTextView.setText(movie.getStudio());
            ratingTextView.setText(movie.getRating());
            yearTextView.setText(movie.getYear());
            descriptionTextView.setText(movie.getDescription());
            Glide.with(this)
                    .load(movie.getPoster())
                    .into(moviePosterImageView);
        }
    }
}
