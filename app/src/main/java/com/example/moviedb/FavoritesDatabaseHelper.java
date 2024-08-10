package com.example.moviedb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class FavoritesDatabaseHelper extends SQLiteOpenHelper {

    // Variable declaration
    private static final String DATABASE_NAME = "favorites.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_FAVORITES = "favorites";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_YEAR = "year";
    private static final String COLUMN_STUDIO = "studio";
    private static final String COLUMN_RATING = "rating";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_POSTER = "poster";

    // Constructor to initialize the database helper
    public FavoritesDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Called when the database is created for the first time
    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create the favorites table
        String CREATE_FAVORITES_TABLE = "CREATE TABLE " + TABLE_FAVORITES + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TITLE + " TEXT,"
                + COLUMN_YEAR + " TEXT,"
                + COLUMN_STUDIO + " TEXT,"
                + COLUMN_RATING + " TEXT,"
                + COLUMN_DESCRIPTION + " TEXT,"
                + COLUMN_POSTER + " TEXT" + ")";
        // Execute the SQL statement
        db.execSQL(CREATE_FAVORITES_TABLE);
    }

    // Called when the database needs to be upgraded
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the old table if it exists and create a new one
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVORITES);
        onCreate(db);
    }

    // Method to add a movie to the favorites table
    public void addFavorite(Movie movie) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, movie.getTitle());
        values.put(COLUMN_YEAR, movie.getYear());
        values.put(COLUMN_STUDIO, movie.getStudio());
        values.put(COLUMN_RATING, movie.getRating());
        values.put(COLUMN_DESCRIPTION, movie.getDescription());
        values.put(COLUMN_POSTER, movie.getPoster());

        // Insert the new row into the favorites table
        db.insert(TABLE_FAVORITES, null, values);
        db.close(); // Close the database connection
    }

    // Method to remove a movie from the favorites table
    public void deleteFavorite(Movie movie) {
        SQLiteDatabase db = this.getWritableDatabase(); // Get writable database instance
        // Delete the movie based on its title and year
        db.delete(TABLE_FAVORITES, COLUMN_TITLE + "=? AND " + COLUMN_YEAR + "=?",
                new String[]{movie.getTitle(), movie.getYear()});
        db.close();
    }

    // Method to check if a movie is in the favorites table
    public boolean isFavorite(Movie movie) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_FAVORITES, new String[]{COLUMN_ID},
                COLUMN_TITLE + "=? AND " + COLUMN_YEAR + "=?",
                new String[]{movie.getTitle(), movie.getYear()}, null, null, null);
        boolean isFavorite = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return isFavorite;
    }

    // Method to get all favorite movies from the database
    public List<Movie> getAllFavorites() {
        List<Movie> favoriteMovies = new ArrayList<>(); // List to store favorite movies
        SQLiteDatabase db = this.getReadableDatabase(); // Get readable database instance
        Cursor cursor = db.query(TABLE_FAVORITES, null, null, null, null, null, null); // Query to get all rows

        // Iterate through the rows and add each movie to the list
        if (cursor.moveToFirst()) {
            do {
                Movie movie = new Movie();
                movie.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE)));
                movie.setYear(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_YEAR)));
                movie.setStudio(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STUDIO)));
                movie.setRating(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_RATING)));
                movie.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION)));
                movie.setPoster(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_POSTER)));
                favoriteMovies.add(movie);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return favoriteMovies;
    }
}
