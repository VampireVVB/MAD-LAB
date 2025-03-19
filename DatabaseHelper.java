package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "student.db";
    public static final String TABLE_NAME = "student_table";

    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "SURNAME";
    public static final String COL_4 = "MARKS";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, SURNAME TEXT, MARKS INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Method to get the student ID based on Name or Surname
    public String getStudentIdByNameOrSurname(String query) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT " + COL_1 + " FROM " + TABLE_NAME + " WHERE " + COL_2 + " LIKE ? OR " + COL_3 + " LIKE ?";
        Cursor cursor = db.rawQuery(selectQuery, new String[]{"%" + query + "%", "%" + query + "%"});

        if (cursor != null && cursor.moveToFirst()) {
            String id = cursor.getString(cursor.getColumnIndex(COL_1));
            cursor.close();
            return id;
        }
        cursor.close();
        return null;  // No record found
    }

    // Method to insert data into the database
    public boolean insertData(String name, String surname, String marks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, surname);
        contentValues.put(COL_4, marks);
        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }
    public boolean deleteDataByName(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_NAME, COL_2 + " = ?", new String[]{name});
        return result > 0;  // Returns true if rows were affected, false otherwise
    }

    // Method to update the data in the database
    public boolean updateData(String id, String name, String surname, String marks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, surname);
        contentValues.put(COL_4, marks);

        int result = db.update(TABLE_NAME, contentValues, COL_1 + " = ?", new String[]{id});
        return result > 0;
    }
}
