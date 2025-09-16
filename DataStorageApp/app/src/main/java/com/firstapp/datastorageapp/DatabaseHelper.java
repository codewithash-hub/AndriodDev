package com.firstapp.datastorageapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyAppDB";
    private static final String TABLE_NAME = "my_table";
    private static final String COL_ID = "id";
    private static final String COL_TEXT = "text";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_TEXT +  " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // INSERT DATA
    public boolean insertData(String text) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_TEXT, text);

        long result = db.insert(TABLE_NAME, null, values);

        return  result != -1;
    }

    // Get last inserted data
    public String getLastData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COL_TEXT + " FROM " + TABLE_NAME + " ORDER BY " + COL_ID + " DESC LIMIT 1", null);

        if (cursor.moveToFirst()) {
            String data = cursor.getString(0);
            cursor.close();
            return data;
        }
        cursor.close();
        return "No data found";
    }
}
