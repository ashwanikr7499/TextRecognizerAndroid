package com.dorvis.textrecognitionandroid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.util.Pair;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String TABLE_NAME = "imagetotextdata";
    public static final String COLUMN_TS = "ts";
    public static final String COLUMN_DESCRIPTION = "description";
    private HashMap hp;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table imagetotextdata " +
                        "(ts text , description text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS imagetotextdata");
        onCreate(db);
    }

    public boolean insertContact (String ts, String text) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ts", ts);
        contentValues.put("description", text);
        db.insert("imagetotextdata", null, contentValues);
        return true;
    }

    public ArrayList<Pair<String, String>> getAllContacts() {
        ArrayList<Pair<String, String>> array_list = new ArrayList<>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from imagetotextdata", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            Pair p = new Pair(res.getString(res.getColumnIndex(COLUMN_TS)),res.getString(res.getColumnIndex(COLUMN_DESCRIPTION)));
            array_list.add(p);
            res.moveToNext();
        }
        return array_list;
    }
    public void deleteAll () {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM imagetotextdata;");
//        return db.delete("contacts",
//                "id = ? ",
//                new String[] { Integer.toString(id) });
    }
//    public Cursor getData(int id) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor res =  db.rawQuery( "select * from imagetotextdata where id="+id+"", null );
//        return res;
//    }
//
//    public int numberOfRows(){
//        SQLiteDatabase db = this.getReadableDatabase();
//        int numRows = (int) DatabaseUtils.queryNumEntries(db, CONTACTS_TABLE_NAME);
//        return numRows;
//    }
//
//    public boolean updateContact (Integer id, String name, String phone, String email, String street,String place) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("name", name);
//        contentValues.put("phone", phone);
//        contentValues.put("email", email);
//        contentValues.put("street", street);
//        contentValues.put("place", place);
//        db.update("contacts", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
//        return true;
//    }
//



}