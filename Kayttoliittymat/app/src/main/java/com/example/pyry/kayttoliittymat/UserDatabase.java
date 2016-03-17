package com.example.pyry.kayttoliittymat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Pyry on 10.3.2016.
 */
public class UserDatabase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "UserData.db";
    public static final String TABLE_NAME = "user_table";
    public static final String COL_1 = "USERNAME";
    public static final String COL_2 = "PASSWORD";
    public static final String COL_3 = "HOUSE";

    public UserDatabase(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY, USERNAME TEXT, PASSWORD TEXT, HOUSE TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String username, String password, String house){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,username);
        contentValues.put(COL_2,password);
        contentValues.put(COL_3, house);
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
    public boolean updateData(String username, String password, String house){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,username);
        contentValues.put(COL_2,password);
        contentValues.put(COL_3, house);
        db.update(TABLE_NAME,contentValues, "USERNAME = ?",new String[] { username });
        return true;
    }
    public Integer deleteData(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"USERNAME = ?",new String[] { username });
    }
}
