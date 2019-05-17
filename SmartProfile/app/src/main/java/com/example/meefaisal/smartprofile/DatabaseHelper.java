package com.example.meefaisal.smartprofile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by MeeFaisal on 3/17/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME ="profiles.db";
    private static final String TABLE_NAME ="profile";
    private static final String TABLE_NAME_TWO ="sleep";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,EMAIL TEXT,GENDER TEXT,POSTCODE TEXT,NUMBER TEXT,LOCATION TEXT,LAT DOUBLE,LON DOUBLE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(String name,String email,String gender,String postal,String number,String location,String lat,String lon){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME",name);
        contentValues.put("EMAIL",email);
        contentValues.put("GENDER",gender);
        contentValues.put("POSTCODE",postal);
        contentValues.put("NUMBER",number);
        contentValues.put("LOCATION",location);
        contentValues.put("LAT",lat);
        contentValues.put("LON",lon);

        long result = db.insert(TABLE_NAME,null,contentValues);

        if(result == -1)
            return false;
        else
            return true;
    }
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME ;
        Cursor cursor = db.rawQuery(query,null);
        return cursor;
    }
    public void deleteData(String number){

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_NAME,"NUMBER = '"+ number +"'",null);
        db.close();

    }
    public void updateData(String name,String email,String gender,String postal,String number,String location,String lat,String lon){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("NAME",name);
        contentValues.put("EMAIL",email);
        contentValues.put("GENDER",gender);
        contentValues.put("POSTCODE",postal);
        contentValues.put("LOCATION",location);
        contentValues.put("LAT",lat);
        contentValues.put("LON",lon);

        db.update(TABLE_NAME, contentValues,"NUMBER = '"+ number +"'",null);
        db.close();
    }
    public Cursor getData2(int id){
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME + " WHERE id = '"+ id +"'";
        Cursor cursor = db.rawQuery(query,null);
        return cursor;
    }

}
