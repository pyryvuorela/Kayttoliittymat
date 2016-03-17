package com.example.pyry.kayttoliittymat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Pyry on 17.3.2016.
 */
public class RoomDatabase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "RoomData.db";
    public static final String TABLE_NAME = "room_table";
    public static final String COL_1 = "ROOMNAME";
    public static final String COL_2 = "LIGHTS";
    public static final String COL_3 = "TEMPERATURE";
    public static final String COL_4 = "LOCK";


    public RoomDatabase(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ROOMNAME TEXT PRIMARY KEY, LIGTHS BOOLEAN, TEAMPERATURE INTEGER, LOCK BOOLEAN)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String roomname,boolean ligths, int temperature, boolean lock){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,roomname);
        contentValues.put(COL_2,ligths);
        contentValues.put(COL_3,temperature);
        contentValues.put(COL_4,lock);
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
    public boolean updateData(String roomname,boolean ligths, int temperature, boolean lock){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, ligths);
        contentValues.put(COL_3, temperature);
        contentValues.put(COL_4, lock);
        db.update(TABLE_NAME, contentValues, "HOUSENAME = ?", new String[]{ roomname });
        return true;
    }
    public Integer deleteData(String roomname){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"HOUSENAME = ?",new String[] { roomname });
    }
}
