package com.example.moviedb;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class FavoritesDatabaseHelper extends SQLiteOpenHelper {

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

    public FavoritesDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_FAVORITES_TABLE = "CREATE TABLE " + TABLE_FAVORITES + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TITLE + " TEXT,"
                + COLUMN_YEAR + " TEXT,"
                + COLUMN_STUDIO + " TEXT,"
                + COLUMN_RATING + " TEXT,"
                + COLUMN_DESCRIPTION + " TEXT,"
                + COLUMN_POSTER + " TEXT" + ")";
        db.execSQL(CREATE_FAVORITES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVORITES);
        onCreate(db);
    }

    public List<Movie> getAllFavorites() {
        List<Movie> favoriteMovies = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_FAVORITES;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Movie movie = new Movie();

                // Check and set each column value safely
                int titleIndex = cursor.getColumnIndex(COLUMN_TITLE);
                if (titleIndex != -1) {
                    movie.setTitle(cursor.getString(titleIndex));
                } else {
                    Log.e("FavoritesDatabaseHelper", "COLUMN_TITLE not found");
                }

                int yearIndex = cursor.getColumnIndex(COLUMN_YEAR);
                if (yearIndex != -1) {
                    movie.setYear(cursor.getString(yearIndex));
                } else {
                    Log.e("FavoritesDatabaseHelper", "COLUMN_YEAR not found");
                }

                int studioIndex = cursor.getColumnIndex(COLUMN_STUDIO);
                if (studioIndex != -1) {
                    movie.setStudio(cursor.getString(studioIndex));
                } else {
                    Log.e("FavoritesDatabaseHelper", "COLUMN_STUDIO not found");
                }

                int ratingIndex = cursor.getColumnIndex(COLUMN_RATING);
                if (ratingIndex != -1) {
                    movie.setRating(cursor.getString(ratingIndex));
                } else {
                    Log.e("FavoritesDatabaseHelper", "COLUMN_RATING not found");
                }

                int descriptionIndex = cursor.getColumnIndex(COLUMN_DESCRIPTION);
                if (descriptionIndex != -1) {
                    movie.setDescription(cursor.getString(descriptionIndex));
                } else {
                    Log.e("FavoritesDatabaseHelper", "COLUMN_DESCRIPTION not found");
                }

                int posterIndex = cursor.getColumnIndex(COLUMN_POSTER);
                if (posterIndex != -1) {
                    movie.setPoster(cursor.getString(posterIndex));
                } else {
                    Log.e("FavoritesDatabaseHelper", "COLUMN_POSTER not found");
                }

                favoriteMovies.add(movie);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return favoriteMovies;
    }
}
