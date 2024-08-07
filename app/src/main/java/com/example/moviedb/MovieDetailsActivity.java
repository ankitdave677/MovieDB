package com.example.moviedb;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class MovieDetailsActivity extends AppCompatActivity {

    private TextView titleTextView;
    private TextView studioTextView;
    private TextView ratingTextView;
    private TextView descriptionTextView;
    private ImageView posterImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        titleTextView = findViewById(R.id.title_text_view);
        studioTextView = findViewById(R.id.studio_text_view);
        ratingTextView = findViewById(R.id.rating_text_view);
        descriptionTextView = findViewById(R.id.description_text_view);
        posterImageView = findViewById(R.id.poster_image_view);

        Movie movie = (Movie) getIntent().getSerializableExtra("movie");

        if (movie != null) {
            titleTextView.setText(movie.getTitle());
            studioTextView.setText(movie.getStudio());
            ratingTextView.setText(movie.getRating());
            descriptionTextView.setText(movie.getDescription());
            Picasso.get().load(movie.getPoster()).into(posterImageView);
        }
    }
}
