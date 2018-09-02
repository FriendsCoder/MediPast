package com.friendscoder.icare.dbhelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DoctorDbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Doctors.db";
    public static final String DOCTORS_TABLE = "doctors_table";
    public static final int DB_VERSION = 1;
    public static final String KEY_ID = "ID";
    public static final String KEY_NAME = "NAME";
    public static final String KEY_DETAILS = "DETAILS";
    public static final String KEY_DATE = "DATE";
    public static final String KEY_NUMBER = "NUMBER";
    public static final String KEY_EMAIL = "EMAIL";
    private SQLiteDatabase db;
    public static final String MEDICAL_HISTORY_TABLE = "medical_history_table";
    public static final String KEY_HISTORY_ID = "ID";
    public static final String KEY_HISTORY_NAME = "DOCTOR_NAME";
    public static final String KEY_HISTORY_DETAILS = "DOCTOR_DETAILS";
    public static final String KEY_HISTORY_PRESCRIPTION_PHOTO = "PRESCRIPTION_PHOTO";
    public static final String KEY_HISTORY_DATE = "HISTORY_DATE";

    private static String doctorTable = "CREATE TABLE " + DOCTORS_TABLE +
            "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_NAME + " TEXT,"
            + KEY_DETAILS + " TEXT,"
            + KEY_DATE + " TEXT,"
            + KEY_NUMBER + " TEXT,"
            + KEY_EMAIL + " TEXT" + ")";

    private static String historyTable = "CREATE TABLE " + MEDICAL_HISTORY_TABLE +
            "(" + KEY_HISTORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_HISTORY_NAME + " TEXT,"
            + KEY_HISTORY_DETAILS + " TEXT,"
            + KEY_HISTORY_PRESCRIPTION_PHOTO + " TEXT,"
            + KEY_HISTORY_DATE + " TEXT" + ")";

    public DoctorDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(doctorTable);
        db.execSQL(historyTable);
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

        long inserted = db.insert(DOCTORS_TABLE, null, contentValues);
        db.close();
        if (inserted > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean insertMedicalHistory(String name, String details, String prescriptionPhoto, String date) {
        db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_HISTORY_NAME, name);
        contentValues.put(KEY_HISTORY_DETAILS, details);
        contentValues.put(KEY_HISTORY_PRESCRIPTION_PHOTO, prescriptionPhoto);
        contentValues.put(KEY_HISTORY_DATE, date);

        long inserted = db.insert(MEDICAL_HISTORY_TABLE, null, contentValues);
        db.close();
        if (inserted > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Cursor getDoctors() {
        db = this.getReadableDatabase();
        String selectData = "SELECT * FROM " + DOCTORS_TABLE;
        Cursor cursor = db.rawQuery(selectData, null);
        return cursor;
    }

    public Cursor getMedicalHistory() {
        db = this.getReadableDatabase();
        String selectData = "SELECT * FROM " + MEDICAL_HISTORY_TABLE;
        Cursor cursor = db.rawQuery(selectData, null);
        return cursor;
    }

    public Cursor getAllDoctorName(){
        db = this.getReadableDatabase();
        String selectData = "SELECT * FROM " + MEDICAL_HISTORY_TABLE;
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

        long updated = db.update(DOCTORS_TABLE, contentValues, "ID = ?", new String[]{id});
        db.close();
        if (updated > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean updateMedicalHistory(String id, String name, String details, String prescriptionPhoto, String date) {
        db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_HISTORY_NAME, name);
        contentValues.put(KEY_HISTORY_DETAILS, details);
        contentValues.put(KEY_HISTORY_PRESCRIPTION_PHOTO, prescriptionPhoto);
        contentValues.put(KEY_HISTORY_DATE, date);

        long updated = db.update(MEDICAL_HISTORY_TABLE, contentValues, "ID = ?", new String[]{id});
        db.close();

        if (updated > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Integer deleteDoctor(String id) {
        db = this.getWritableDatabase();
        return db.delete(DOCTORS_TABLE, "ID = ?", new String[]{id});
    }

    public Integer deleteMedicalHistory(String id) {
        db = this.getWritableDatabase();
        return db.delete(MEDICAL_HISTORY_TABLE, "ID = ?", new String[]{id});
    }
}
