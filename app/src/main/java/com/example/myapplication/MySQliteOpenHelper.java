package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.myapplication.Docter;

public class MySQliteOpenHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 3;
    private static final String DB_NAME="Docinfo.db";

    private static final String Create_doc="create table docter(id Integer primary key autoincrement,docname varchar(32),docpw varchar(32))";

    public static final String Create_Item = "create table Item("
            +" id integer primary key autoincrement,"
            +" name text,"+" age integer,"
            +" sex text,"
            +" itemname text,"
            +" doc text,"
            +" starttime date, "
            +" charge boolean,"
            +" knowsitu boolean)";
    public MySQliteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Create_doc);
        sqLiteDatabase.execSQL(Create_Item);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//        if (i<DATABASE_VERSION){
//            sqLiteDatabase.execSQL("ALTER TABLE Item ADD COLUMN  docter TEXT");
//        }
    }
//    public long register(Docter doc){
//        SQLiteDatabase db = getWritableDatabase();
//        ContentValues cv = new ContentValues();
//        cv.put("name",doc.getName());
//        cv.put("password",doc.getPassword());
//        long docs = db.insert(DB_NAME,null,cv);
//        return docs;
//    }
//    public void login(String name,String password){
//        SQLiteDatabase db1 = getWritableDatabase();
//
//    }
}

