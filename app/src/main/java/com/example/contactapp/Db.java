package com.example.contactapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class Db extends SQLiteOpenHelper {

    public static String db_name = "contacts.db";
    public static String table_name = "contact";
    public static String col1 = "name";
    public static String col2 = "gender";
    public static String col3 = "number";
    public static String col4 = "picture";


    public Db(Context context) {
        super(context, db_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = String.format("create table %s(%s text,%s text,%s text primary key,%s integer)", table_name, col1, col2, col3, col4);
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "drop table " + table_name;
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }

    public static long insertContact(SQLiteDatabase sqLiteDatabase, Contact contact) {
        ContentValues cv = new ContentValues();
        cv.put(col1, contact.getName());
        cv.put(col2, contact.getGender());
        cv.put(col3, contact.getNumber());
        cv.put(col4, contact.getPicture());
        return sqLiteDatabase.insert(table_name, null, cv);
    }

    public static long updateContact(SQLiteDatabase sqLiteDatabase, Contact contact) {
        ContentValues cv = new ContentValues();
        cv.put(col1, contact.getName());
        cv.put(col2, contact.getName());
        cv.put(col4, contact.getName());
        return sqLiteDatabase.update(table_name, cv, col3 + "=" + contact.getNumber(), null);
    }

    public static long deleteContact(SQLiteDatabase sqLiteDatabase, String number) {

        return sqLiteDatabase.delete(table_name, col3 + "=" + number, null);
    }

    public static ArrayList getAllContact(SQLiteDatabase sqLiteDatabase) {
        Cursor c = sqLiteDatabase.rawQuery("select * from " + table_name, null);
        ArrayList<Contact> con = new ArrayList<>();
        while (c.moveToNext()) {
            con.add(new Contact(c.getString(0), c.getString(1), c.getString(2), c.getInt(3)));

        }
        return con;
    }

    public static Contact getContact(SQLiteDatabase sqLiteDatabase, String number) {
        Contact co = null;
        Cursor c = sqLiteDatabase.rawQuery("select * from " + table_name + " where " + col3 + "=" + number, null);
        if (c.moveToNext())
            co = new Contact(c.getString(0), c.getString(1), c.getString(2), c.getInt(3));
        return co;
    }
}
