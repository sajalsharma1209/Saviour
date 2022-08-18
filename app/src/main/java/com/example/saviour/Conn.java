package com.example.saviour;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Conn extends SQLiteOpenHelper {
    private static final String databasename = "database.db";

    public Conn(@Nullable Context context) {
        super(context, databasename, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE members (\n" +
                "    id          INTEGER PRIMARY KEY ASC AUTOINCREMENT\n" +
                "                        NOT NULL,\n" +
                "    name      STRING  NOT NULL,\n" +
                "    mobile        STRING  NOT NULL);");

        sqLiteDatabase.execSQL("CREATE TABLE sosmessage (\n" +
                "    id      INTEGER PRIMARY KEY ASC AUTOINCREMENT\n" +
                "                    NOT NULL,\n" +
                "    message STRING NOT NULL\n" +
                ");\n");
        sqLiteDatabase.execSQL("insert into sosmessage values(null,\"HELP Me! IT'S AN EMERGANCY\");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public boolean insert_members(String name, String mobile) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("name", name);
        c.put("mobile", mobile);
        long r = db.insert("members", null, c);
        return r != -1;
    }

    public Cursor get_members() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM members;", null);
    }


    public Cursor get_message() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from sosmessage;", null);
    }

    public boolean update_message(String message) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update sosmessage set message='" + message + "' where id=1;");
        return true;
    }

    public void update_member(String id, String name, String mobile) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update members set name='" + name + "',mobile='" + mobile + "' where id='" + id + "';");
    }

    public void delete_member(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from members where id='" + id + "';");
    }
}
