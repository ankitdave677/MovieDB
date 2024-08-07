package com.example.moviedb;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class FavoritesDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "favorites.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_FAVORITES = "favorites";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_YEAR = "year";
    private static final String COLUMN_IMDB_ID = "imdb_id";
    private static final String COLUMN_TYPE = "type";
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
                + COLUMN_IMDB_ID + " TEXT,"
                + COLUMN_TYPE + " TEXT,"
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
                movie.setTitle(cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)));
                movie.setYear(cursor.getString(cursor.getColumnIndex(COLUMN_YEAR)));
                movie.setImdbID(cursor.getString(cursor.getColumnIndex(COLUMN_IMDB_ID)));
                movie.setType(cursor.getString(cursor.getColumnIndex(COLUMN_TYPE)));
                movie.setPoster(cursor.getString(cursor.getColumnIndex(COLUMN_POSTER)));
                favoriteMovies.add(movie);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return favoriteMovies;
    }
}
