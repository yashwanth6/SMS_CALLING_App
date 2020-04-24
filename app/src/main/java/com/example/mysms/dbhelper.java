package com.example.mysms;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dbhelper extends SQLiteOpenHelper {
    public dbhelper(Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table user (uname text,email text primary key, password text,phoneno text )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists  user ");
    }
    public Boolean insert(String uname,String email,String password,String phoneno){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("uname",uname);
        contentValues.put("email",email);
        contentValues.put("password",password);
        contentValues.put("phoneno",phoneno);
        long ins=db.insert("user",null,contentValues);
        if(ins==-1) return  false;
        else return true;
    }
    public Boolean checkmail(String email){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor= db.rawQuery(" Select * from user where email= ? ",new String[] {email});
        if(cursor.getCount()>0) return false;
        else return true;
    }
    public Boolean emailpassword(String email,String password){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor= db.rawQuery(" Select * from user where email= ? and password= ? ",new String[] {email,password});
        if(cursor.getCount()>0) return true;
        else return false;
    }


}
