package com.friendscoder.icare.dbhelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DoctorDbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Doctors.db";
    public static final String TABLE_NAME = "doctors_table";
    public static final int DB_VERSION = 1;
    public static final String KEY_ID = "ID";
    public static final String KEY_NAME = "NAME";
    public static final String KEY_DETAILS = "DETAILS";
    public static final String KEY_DATE = "DATE";
    public static final String KEY_NUMBER = "NUMBER";
    public static final String KEY_EMAIL = "EMAIL";
    private SQLiteDatabase db;

    private static String createTable = "CREATE TABLE " + TABLE_NAME +
            "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_NAME + " TEXT,"
            + KEY_DETAILS + " TEXT,"
            + KEY_DATE + " TEXT,"
            + KEY_NUMBER + " TEXT,"
            + KEY_EMAIL + " TEXT" + ")";

    public DoctorDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insertDoctor(String name, String details, String date, String number, String email) {
        db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME, name);
        contentValues.put(KEY_DETAILS, details);
        contentValues.put(KEY_DATE, date);
        contentValues.put(KEY_NUMBER, number);
        contentValues.put(KEY_EMAIL, email);

        long inserted = db.insert(TABLE_NAME, null, contentValues);
        db.close();
        if (inserted > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Cursor getDoctors() {
        db = this.getReadableDatabase();
        String selectData = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(selectData, null);

        return cursor;
    }

    public boolean updateDoctor(String id, String name, String details, String date, String number, String email) {
        db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_ID, id);
        contentValues.put(KEY_NAME, name);
        contentValues.put(KEY_DETAILS, details);
        contentValues.put(KEY_DATE, date);
        contentValues.put(KEY_NUMBER, number);
        contentValues.put(KEY_EMAIL, email);

        long updated = db.update(TABLE_NAME, contentValues, "ID = ?", new String[]{id});
        db.close();
        if (updated > 0) {
            return true;
        } else {
            return false;
        }
    }


    public Integer deleteDoctor(String id) {
         db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[]{id});
    }
}
