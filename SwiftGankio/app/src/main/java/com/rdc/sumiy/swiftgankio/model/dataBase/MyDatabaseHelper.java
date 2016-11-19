package com.rdc.sumiy.swiftgankio.model.dataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by sumiy on 2016/8/14.
 */
public class MyDatabaseHelper extends SQLiteOpenHelper {
    public static final String CREATE_ANDROID = "create table Android("
            + "id integer  primary key autoincrement , "
            + "url text , "
            + "desc text, "
            + "createdAt text, "
            + "who text)";
    public static final String CREATE_FULI = "create table Fuli("
            + "id integer  primary key autoincrement , "
            + "url text)";
    public static final String CREATE_ViDEO = "create table Video("
            + "id integer  primary key autoincrement , "
            + "url text , "
            + "desc text, "
            + "createdAt text, "
            + "who text)";
    public static final String CREATE_iOS = "create table iOS("
            + "id integer  primary key autoincrement , "
            + "url text , "
            + "desc text, "
            + "createdAt text, "
            + "who text)";
    public static final String CREATE_QIANDUAN = "create table Qianduan("
            + "id integer  primary key autoincrement , "
            + "url text , "
            + "desc text, "
            + "createdAt text, "
            + "who text)";
    public static final String CREATE_EXPAND = "create table Expand("
            + "id integer  primary key autoincrement , "
            + "url text , "
            + "desc text, "
            + "createdAt text, "
            + "who text)";
    public static final String CREATE_XIA = "create table Xia("
            + "id integer  primary key autoincrement , "
            + "url text , "
            + "desc text, "
            + "createdAt text, "
            + "who text)";
    private Context mContext;

    public MyDatabaseHelper(Context context, String name,
                            SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ANDROID);
        Toast.makeText(mContext, "create succeeded", Toast.LENGTH_LONG).show();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion) {
            case 2:
                db.execSQL("drop table if exists Android");
                db.execSQL(CREATE_ANDROID);
                db.execSQL(CREATE_FULI);
                db.execSQL(CREATE_ViDEO);
                db.execSQL(CREATE_iOS);
                db.execSQL(CREATE_QIANDUAN);
                db.execSQL(CREATE_EXPAND);
                db.execSQL(CREATE_XIA);
                break;
        }
    }
}
