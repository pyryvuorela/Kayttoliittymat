package com.example.pyry.kayttoliittymat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Pyry on 17.3.2016.
 */
public class HouseDatabase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "HouseData.db";
    public static final String TABLE_NAME = "house_table";
    public static final String COL_1 = "HOUSENAME";
    public static final String COL_2 = "ROOM1";
    public static final String COL_3 = "ROOM1LIGHT";
    public static final String COL_4 = "ROOM1LOCK";
    public static final String COL_5 = "ROOM1TEMP";
    public static final String COL_6 = "ROOM2";
    public static final String COL_7 = "ROOM2LIGHT";
    public static final String COL_8 = "ROOM2LOCK";
    public static final String COL_9 = "ROOM2TEMP";
    public static final String COL_10 = "ROOM3";
    public static final String COL_11 = "ROOM3LIGHT";
    public static final String COL_12 = "ROOM3LOCK";
    public static final String COL_13 = "ROOM3TEMP";


    public HouseDatabase(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (HOUSENAME TEXT PRIMARY KEY, ROOM1 BOOLEAN, ROOM1LIGHT BOOLEAN, ROOM1LOCK BOOLEAN, ROOM1TEMP BOOLEAN, ROOM2 BOOLEAN, ROOM2LIGHT BOOLEAN, ROOM2LOCK BOOLEAN, ROOM2TEMP BOOLEAN, ROOM3 BOOLEAN, ROOM3LIGHT BOOLEAN, ROOM3LOCK BOOLEAN, ROOM3TEMP BOOLEAN)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String housename,boolean room1, boolean room1light, boolean room1lock, boolean room1temp,boolean room2, boolean room2light, boolean room2lock, boolean room2temp,boolean room3, boolean room3light, boolean room3lock, boolean room3temp){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,housename);
        contentValues.put(COL_2,room1);
        contentValues.put(COL_3,room1light);
        contentValues.put(COL_4,room1lock);
        contentValues.put(COL_5,room1temp);
        contentValues.put(COL_6,room2);
        contentValues.put(COL_7,room2light);
        contentValues.put(COL_8,room2lock);
        contentValues.put(COL_9,room2temp);
        contentValues.put(COL_10,room3);
        contentValues.put(COL_11,room3light);
        contentValues.put(COL_12,room3lock);
        contentValues.put(COL_13,room3temp);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }
    public boolean updateData(String housename,boolean room1, boolean room1light, boolean room1lock, boolean room1temp,boolean room2, boolean room2light, boolean room2lock, boolean room2temp,boolean room3, boolean room3light, boolean room3lock, boolean room3temp){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,housename);
        contentValues.put(COL_2,room1);
        contentValues.put(COL_3,room1light);
        contentValues.put(COL_4,room1lock);
        contentValues.put(COL_5,room1temp);
        contentValues.put(COL_6,room2);
        contentValues.put(COL_7,room2light);
        contentValues.put(COL_8,room2lock);
        contentValues.put(COL_9,room2temp);
        contentValues.put(COL_10,room3);
        contentValues.put(COL_11,room3light);
        contentValues.put(COL_12,room3lock);
        contentValues.put(COL_13,room3temp);
        db.update(TABLE_NAME, contentValues, "HOUSENAME = ?", new String[]{housename});
        return true;
    }
    public Integer deleteData(String housename){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"HOUSENAME = ?",new String[] { housename });
    }
}
