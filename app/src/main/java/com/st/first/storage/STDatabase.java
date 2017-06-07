package com.st.first.storage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class STDatabase extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "st.db";
    public static final String COURSES_TABLE_NAME = "courses";
    public static final String COURSES_ID = "_id";
    public static final String COURSES_NAME = "name";
    public static final String COURSES_FEE = "fee";
    public static final String COURSES_DURATION = "duration";

    private static final String TABLE_CREATE_SQL =
            "CREATE TABLE " + COURSES_TABLE_NAME + "( " + COURSES_ID +
                    " integer primary key autoincrement, " + COURSES_NAME + " TEXT,  " +
                    COURSES_FEE + " INTEGER, " + COURSES_DURATION + " INTEGER)";

    public STDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("Storage", "STDatabase()");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("Storage", "STDatabase.onCreate()");
        db.execSQL(TABLE_CREATE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("Storage", "STDatabase.onUpgrade()");
    }

}
