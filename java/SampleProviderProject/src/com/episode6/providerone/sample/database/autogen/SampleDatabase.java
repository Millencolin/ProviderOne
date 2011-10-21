package com.episode6.providerone.sample.database.autogen;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.episode6.providerone.sample.database.tables.MyTableInfo;

public class SampleDatabase extends SQLiteOpenHelper {
    
    
    public static final String DB_NAME = "sample.sqlite";
    public static final int DB_VERSION = 1;

    public static final String IDX_CREATE_SAMPLE_IDX = "CREATE INDEX \"sample_idx\" ON \"my_table\" (\"my_double\")";
    public static final String IDX_DROP_SAMPLE_IDX = "DROP INDEX IF EXISTS \"sample_idx\"";
    
    
    public SampleDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        MyTableInfo.createTable(db);
        db.execSQL(IDX_DROP_SAMPLE_IDX);
        db.execSQL(IDX_CREATE_SAMPLE_IDX);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        MyTableInfo.upgradeTable(db);
        db.execSQL(IDX_DROP_SAMPLE_IDX);
        db.execSQL(IDX_CREATE_SAMPLE_IDX);
    }
    
}
