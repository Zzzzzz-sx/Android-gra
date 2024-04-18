package com.example.myapplication.SQliteOpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.Docter;

public class MySQliteOpenHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 3;
    private static final String DB_NAME="Docinfo.db";
//    private static final String DB_NAME2 = "Patinfo.db";

    private static final String Create_doc="create table docter(id Integer primary key autoincrement,doc_login_name varchar(32),doc_login_pw varchar(32),docter_name varchar(32))";

    public static final String Create_Item = "create table Item("
            +" id integer primary key autoincrement,"
            +" name text,"+" age integer,"
            +" sex text,"
            +" itemname text,"
            +" doc text,"
            +" starttime date, "
            +" chargenum integer, "
            +" charge boolean,"
            +" knowsitu boolean)";
    public static final String Create_news = "create table News(id Integer primary key autoincrement,title varchar(32),content varchar(32))";
    public MySQliteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Create_doc);
        sqLiteDatabase.execSQL(Create_Item);
        sqLiteDatabase.execSQL(Create_news);
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

