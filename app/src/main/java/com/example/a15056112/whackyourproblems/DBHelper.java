package com.example.a15056112.whackyourproblems;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 15056112 on 11/6/2017.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "highscore.db";
    private static final String TABLE_HIGHSCORE = "highscore";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_SCORE = "score";



    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSql = "CREATE TABLE " + TABLE_HIGHSCORE + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_SCORE + " INTEGER " + ");";

        db.execSQL(createTableSql);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HIGHSCORE);
        onCreate(db);
    }

    public void addHighScore(int highscore) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SCORE, highscore);
        db.insert(TABLE_HIGHSCORE,null,values);
        db.close();
    }


    public ArrayList<HashMap<String,Integer>> getAllScores() {
        ArrayList<HashMap<String,Integer>> scoresList = new ArrayList<>();

        String selectQuery = "SELECT " + COLUMN_ID + ", " + COLUMN_SCORE + " FROM " + TABLE_HIGHSCORE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, Integer> hashMap = new HashMap<>();
                int id = cursor.getInt(0);
                int score = cursor.getInt(1);
                hashMap.put("id", id);
                hashMap.put("score", score);
                scoresList.add(hashMap);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return scoresList;
    }

}
