package com.example.meefaisal.smartprofile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper3 extends SQLiteOpenHelper {
    private static final String DATABASE_NAME ="reminder.db";
    private static final String TABLE_NAME_THREE ="reminder";

    public DatabaseHelper3(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ TABLE_NAME_THREE +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,DATE TEXT,TIME TEXT,LOCATION TEXT,MESSAGE TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME_THREE);
        onCreate(db);

    }
    public boolean insertDataReminder(String name,String date,String time,String location,String message){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME",name);
        contentValues.put("DATE",date);
        contentValues.put("TIME",time);
        contentValues.put("LOCATION",location);
        contentValues.put("MESSAGE",message);

        long result = db.insert(TABLE_NAME_THREE,null,contentValues);

        if(result == -1)
            return false;
        else
            return true;
    }
    public Cursor getDataReminder(){
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME_THREE ;
        Cursor cursor = db.rawQuery(query,null);
        return cursor;
    }
    public void updateDataReminder(String name, String date, String time, String location, String message,int id){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME",name);
        contentValues.put("DATE",date);
        contentValues.put("TIME",time);
        contentValues.put("LOCATION",location);
        contentValues.put("MESSAGE",message);

        db.update(TABLE_NAME_THREE, contentValues,"id = '"+ id +"'",null);
        db.close();

    }
    public Cursor getDataReminder2(int id){
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME_THREE + " WHERE id = '"+ id +"'";
        Cursor cursor = db.rawQuery(query,null);
        return cursor;
    }
    public void deleteDataReminder(int id){

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_NAME_THREE,"id = '"+ id +"'",null);
        db.close();

    }
}
