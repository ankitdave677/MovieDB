package com.example.moviedb;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private List<Movie> movies;
    private OnItemClickListener listener;
    private FavoritesDatabaseHelper dbHelper;

    public interface OnItemClickListener {
        void onItemClick(Movie movie);
    }

    // Constructor for the adapter
    public MovieAdapter(List<Movie> movies, OnItemClickListener listener, FavoritesDatabaseHelper dbHelper) {
        this.movies = movies;
        this.listener = listener;
        this.dbHelper = dbHelper;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.titleTextView.setText(movie.getTitle());
        holder.yearTextView.setText(movie.getYear());
        holder.studioTextView.setText(movie.getStudio());
        holder.ratingTextView.setText(movie.getRating());
        Glide.with(holder.posterImageView.getContext()).load(movie.getPoster()).into(holder.posterImageView);

        // Update wishlist icon based on whether the movie is in favorites
        if (dbHelper.isFavorite(movie)) {
            holder.wishlistIcon.setImageResource(R.drawable.ic_wishlist_filled);
        } else {
            holder.wishlistIcon.setImageResource(R.drawable.ic_wishlist_empty);
        }

        // Handle the click event for the wishlist icon to add/remove favorites
        holder.wishlistIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dbHelper.isFavorite(movie)) {
                    dbHelper.deleteFavorite(movie);
                    holder.wishlistIcon.setImageResource(R.drawable.ic_wishlist_empty);
                    Toast.makeText(v.getContext(), "Removed from favorites", Toast.LENGTH_SHORT).show();
                } else {
                    dbHelper.addFavorite(movie);
                    holder.wishlistIcon.setImageResource(R.drawable.ic_wishlist_filled);
                    Toast.makeText(v.getContext(), "Added to favorites", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Handle the click event for the entire item view
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(movie);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    // ViewHolder class to hold the views
    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;
        public TextView yearTextView;
        public TextView studioTextView;
        public TextView ratingTextView;
        public ImageView posterImageView;
        public ImageView wishlistIcon;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.movie_title);
            yearTextView = itemView.findViewById(R.id.movie_year);
            studioTextView = itemView.findViewById(R.id.movie_studio);
            ratingTextView = itemView.findViewById(R.id.movie_rating);
            posterImageView = itemView.findViewById(R.id.movie_poster);
            wishlistIcon = itemView.findViewById(R.id.wishlist_icon);
        }
    }
}