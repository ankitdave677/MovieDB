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

    public void addFavorite(Movie movie) {
        SQLiteDatabase db = this.getWritableDatabase();
        String INSERT_FAVORITE = "INSERT INTO " + TABLE_FAVORITES + " ("
                + COLUMN_TITLE + ", "
                + COLUMN_YEAR + ", "
                + COLUMN_STUDIO + ", "
                + COLUMN_RATING + ", "
                + COLUMN_DESCRIPTION + ", "
                + COLUMN_POSTER + ") VALUES ('"
                + movie.getTitle() + "', '"
                + movie.getYear() + "', '"
                + movie.getStudio() + "', '"
                + movie.getRating() + "', '"
                + movie.getDescription() + "', '"
                + movie.getPoster() + "')";
        db.execSQL(INSERT_FAVORITE);
        db.close();
    }

    public List<Movie> getAllFavorites() {
        List<Movie> favoriteMovies = new ArrayList<>();
        String SELECT_ALL = "SELECT * FROM " + TABLE_FAVORITES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(SELECT_ALL, null);
        if (cursor.moveToFirst()) {
            do {
                Movie movie = new Movie();
                movie.setTitle(cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)));
                movie.setYear(cursor.getString(cursor.getColumnIndex(COLUMN_YEAR)));
                movie.setStudio(cursor.getString(cursor.getColumnIndex(COLUMN_STUDIO)));
                movie.setRating(cursor.getString(cursor.getColumnIndex(COLUMN_RATING)));
                movie.setDescription(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)));
                movie.setPoster(cursor.getString(cursor.getColumnIndex(COLUMN_POSTER)));
                favoriteMovies.add(movie);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return favoriteMovies;
    }

    public void deleteFavorite(Movie movie) {
        SQLiteDatabase db = this.getWritableDatabase();
        String DELETE_FAVORITE = "DELETE FROM " + TABLE_FAVORITES + " WHERE "
                + COLUMN_TITLE + " = '" + movie.getTitle() + "' AND "
                + COLUMN_YEAR + " = '" + movie.getYear() + "'";
        db.execSQL(DELETE_FAVORITE);
        db.close();
    }
}
