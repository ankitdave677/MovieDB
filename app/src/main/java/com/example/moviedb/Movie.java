package com.example.moviedb;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Movie implements Parcelable {
    private String title;
    private String studio;
    private String rating;
    private String description;
    private String poster;
    private String year;

    // Default constructor
    public Movie() {
    }

    // Constructor with parameters
    public Movie(String title, String studio, String rating, String description, String poster, String year) {
        this.title = title;
        this.studio = studio;
        this.rating = rating;
        this.description = description;
        this.poster = poster;
        this.year = year;
    }

    // Getter and Setter methods
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    // Parcelable implementation
    protected Movie(Parcel in) {
        title = in.readString();
        studio = in.readString();
        rating = in.readString();
        description = in.readString();
        poster = in.readString();
        year = in.readString();
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(studio);
        parcel.writeString(rating);
        parcel.writeString(description);
        parcel.writeString(poster);
        parcel.writeString(year);
    }
}
