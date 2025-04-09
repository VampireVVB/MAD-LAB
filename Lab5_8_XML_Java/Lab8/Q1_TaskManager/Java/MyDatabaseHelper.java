package com.example.l8q1taskmanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyDatabase.db";
    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_TABLE =
            "CREATE TABLE TaskManager (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name TEXT NOT NULL, " +
                    "due TEXT NOT NULL," +
                    "prior TEXT NOT NULL);";
    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        db.execSQL("INSERT into TaskManager (name, due, prior) values ('Android Studio','2025-04-01', 'High');");
        db.execSQL("INSERT into TaskManager (name, due, prior) values ('Calendar','2025-03-31', 'Low');");
        db.execSQL("INSERT into TaskManager (name, due, prior) values ('Teams','2025-02-28', 'Medium');");
        db.execSQL("INSERT into TaskManager (name, due, prior) values ('WhatsApp','2025-03-31', 'Medium');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS TaskManager");
        onCreate(db);
    }
}

