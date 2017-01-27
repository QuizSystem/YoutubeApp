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

   public Database(Context context) {
       super(context, "thieumao.sqlite", null, 1);
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
