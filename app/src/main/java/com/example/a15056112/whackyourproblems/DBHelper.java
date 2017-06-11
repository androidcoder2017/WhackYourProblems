package com.example.a15056112.whackyourproblems;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;
import java.util.List;

/**
 * Created by 15056112 on 11/6/2017.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final String TAG = "Database";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Level";
    private static final String USERTABLE = "USER_INFO";
    private static final String KEY_ID = "id";
    private static final String LEVEL1 = "LEVEL1";
    private static final String LEVEL2 = "LEVEL2";

    private static final String CREATE_TABLE_USER = "CREATE TABLE IF NOT EXISTS "
            + USERTABLE + "(" + KEY_ID + " INTEGER PRIMARY KEY," + LEVEL1 + " TEXT,"
            + LEVEL2 + " TEXT"+");";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + USERTABLE;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e(TAG,"Inside onCreate: creating table");
        db.execSQL(CREATE_TABLE_USER);
    }

    //to save score in db according to the level
    public void saveData(int level,String score){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues data = new ContentValues();
        switch(level){
            case 1:
                data.put(LEVEL1, score);
                break;
            case 2:
                data.put(LEVEL2,score);
                break;
        }
        db.insert(USERTABLE,null,data);
        db.close();
    }


    //to get score from db
    //you can modify this function if you want to get score for a specific level
    public HashMap<String,String> getData(){
        HashMap<String,String> scoreMap = new HashMap<>();
        String selectQuery = "SELECT  * FROM " + USERTABLE ;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {

                scoreMap.put(LEVEL1,cursor.getString(cursor.getColumnIndex(LEVEL1)));
                scoreMap.put(LEVEL2,cursor.getString(cursor.getColumnIndex(LEVEL2)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return scoreMap;

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.e(TAG,"Inside onUpgrade");
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
