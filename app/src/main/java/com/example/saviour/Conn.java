package com.example.saviour;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Conn extends SQLiteOpenHelper {
    private  static final String databasename="database.db";
    public Conn(@Nullable Context context) {
        super(context,databasename,null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE members (\n" +
                "    id          INTEGER PRIMARY KEY ASC AUTOINCREMENT\n" +
                "                        NOT NULL,\n" +
                "    name      STRING  NOT NULL,\n" +
                "    mobile        STRING  NOT NULL);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean insert_members(String name,String mobile)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues c=new ContentValues();
        c.put("name",name);
        c.put("mobile",mobile);

        long r=db.insert("members",null,c);

        return r!=-1;
    }

    public String get_row() {
        SQLiteDatabase db = this.getWritableDatabase();

        @SuppressLint("Recycle") Cursor number=db.rawQuery("SELECT * FROM members;",null);

        return String.valueOf(number.getCount());
    }
}
