package com.example.pyry.kayttoliittymat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Vuorela on 28.3.2016.
 */
public class RoomDatabase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "RoomData.db";
    public static final String TABLE_NAME = "room_table";
    public static final String COL_1 = "ROOMNAME";
    public static final String COL_2 = "LIGTH";
    public static final String COL_3 = "LOCK";
    public static final String COL_4 = "TEMP";
    public static final String COL_5 = "HOUSEINC";



    public RoomDatabase(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY, ROOMNAME TEXT, LIGTH BOOLEAN, LOCK BOOLEAN, TEMP INTEGER, HOUSEINC TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String roomname, Boolean ligth, Boolean lock, int temp, String houseinc){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,roomname);
        contentValues.put(COL_2, ligth);
        contentValues.put(COL_3,lock);
        contentValues.put(COL_4, temp);
        contentValues.put(COL_5, houseinc);
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
    public boolean updateData(String roomname, Boolean ligth, Boolean lock, int temp, String houseinc){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, ligth);
        contentValues.put(COL_3, lock);
        contentValues.put(COL_4, temp);
        contentValues.put(COL_5, houseinc);
        db.update(TABLE_NAME,contentValues, "ROOMNAME = ?",new String[] { roomname });
        return true;
    }
    public Integer deleteData(String roomname){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ROOMNAME = ?",new String[] { roomname });
    }
}
