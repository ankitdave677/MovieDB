package com.example.moviedb;

import java.io.Serializable;

public class Movie implements Serializable {
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
    public Movie(String title, String studio, String rating, String description, String poster) {
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
}

