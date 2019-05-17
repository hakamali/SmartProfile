package com.example.meefaisal.smartprofile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by MeeFaisal on 3/18/2018.
 */

public class DatabaseHelper2 extends SQLiteOpenHelper {
    private static final String DATABASE_NAME ="sleepnew.db";
    private static final String TABLE_NAME_TWO ="sleepn";

    public DatabaseHelper2(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ TABLE_NAME_TWO +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,START TEXT,STARTMIN TEXT,ENDI TEXT,ENDIMIN TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME_TWO);
        onCreate(db);

    }
    public boolean insertDataSleep(String start,String startMin,String endi,String endiMin){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("START",start);
        contentValues.put("STARTMIN",startMin);
        contentValues.put("ENDI",endi);
        contentValues.put("ENDIMIN",endiMin);

        long result = db.insert(TABLE_NAME_TWO,null,contentValues);

        if(result == -1)
            return false;
        else
            return true;
    }
    public Cursor getDataSleep(){
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME_TWO ;
        Cursor cursor = db.rawQuery(query,null);
        return cursor;
    }
    public void updateDataSleep(String start,String startMin,String endi,String endiMin){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("START",start);
        contentValues.put("STARTMIN",startMin);
        contentValues.put("ENDI",endi);
        contentValues.put("ENDIMIN",endiMin);

        db.update(TABLE_NAME_TWO, contentValues,"id = '"+ 1 +"'",null);
        db.close();
    }
}
