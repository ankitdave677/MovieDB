package com.example.moviedb;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private List<Movie> movieList;
    private OnItemClickListener onItemClickListener;

    public MovieAdapter(List<Movie> movieList, OnItemClickListener onItemClickListener) {
        this.movieList = movieList;
        this.onItemClickListener = onItemClickListener;
    }

    public void setMovies(List<Movie> movies) {
        this.movieList = movies;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        holder.titleTextView.setText(movie.getTitle());
        holder.studioTextView.setText(movie.getStudio());
        holder.ratingTextView.setText(movie.getRating());
        holder.yearTextView.setText(movie.getYear());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(movie);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Movie movie);
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView studioTextView;
        TextView ratingTextView;
        TextView yearTextView;

        MovieViewHolder(View view) {
            super(view);
            titleTextView = view.findViewById(R.id.titleTextView);
            studioTextView = view.findViewById(R.id.studio_text_view);
            ratingTextView = view.findViewById(R.id.rating_text_view);
            yearTextView = view.findViewById(R.id.year_text_view);
        }
    }
}
