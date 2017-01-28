package com.thieumao.youtubeapp.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by thieumao on 1/27/17.
 */

public class Database extends SQLiteOpenHelper {

    private static Database instance = null;
    private static String sqlCreateUserTable = "CREATE TABLE IF NOT EXISTS user (_id INTEGER PRIMARY KEY, username VARCHAR(200) NOT NULL UNIQUE, password VARCHAR(200) NOT NULL, fullname VARCHAR(200) )";
    private static String sqlCreateHistoryTable = "CREATE TABLE IF NOT EXISTS history (_id INTEGER PRIMARY KEY, title VARCHAR(200) NOT NULL, idVideoYoutube VARCHAR(200) NOT NULL, idUser INTEGER NOT NULL)";

    private Database(Context context) {
        super(context, "thieumao.sqlite", null, 1);
    }

    public static Database getInstance(Context context) {
        if (instance == null) {
            instance = new Database(context);
            instance.queryData(sqlCreateUserTable);
            instance.queryData(sqlCreateHistoryTable);
        }
        return  instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    public Cursor getData(String sql) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery(sql, null);
        return c;
    }

    public Boolean queryData(String sql) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            db.execSQL(sql);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}
