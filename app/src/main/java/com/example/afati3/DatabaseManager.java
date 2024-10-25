package com.example.afati3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseManager extends SQLiteOpenHelper{

    public final static String DATABASE_NAME = "Afati3";
    public final static String TABLE_NAME = "user_date";
    public final static String COLUMN_ID = "_id";
    public final static String COLUMN_NAME = "name";
    public final static String COLUMN_SURNAME = "surname";
    public final static String COLUMN_BRUTO = "bruto";
    public final static String COLUMN_PENSION_CONTRIBUTION = "pension_contribution";
    public final static String COLUMN_WAGE_WITHOUT_TAX = "taxed_wage";


    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME,  null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ( "
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME + " TEXT, "
                + COLUMN_SURNAME + " TEXT, "
                + COLUMN_BRUTO + " REAL, "
                + COLUMN_PENSION_CONTRIBUTION+ " REAL, "
                + COLUMN_WAGE_WITHOUT_TAX + " REAL);";
        sqLiteDatabase.execSQL(createTable);
    }

    public boolean insertUser(UserDataModel userDataModel) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, userDataModel.getUsername());
        cv.put(COLUMN_SURNAME, userDataModel.getSurname());
        cv.put(COLUMN_BRUTO, userDataModel.getPagaBruto());
        cv.put(COLUMN_PENSION_CONTRIBUTION, userDataModel.getKontributiPensional());
        cv.put(COLUMN_WAGE_WITHOUT_TAX, userDataModel.getPagaTatueshme());


        return db.insert(TABLE_NAME, null, cv) != -1;
    }
    public boolean deleteUser(int id) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_NAME, COLUMN_ID + "=?",new String[]{String.valueOf(id)}) > 0;
    }
    public Cursor getAllData() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int v, int i) {

    }
}
