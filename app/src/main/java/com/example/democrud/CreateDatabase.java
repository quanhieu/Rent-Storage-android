package com.example.democrud;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CreateDatabase extends SQLiteOpenHelper {

    public CreateDatabase(Context context) {
        super(context, "Storage", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String STORAGE = " CREATE TABLE STORAGE ( " +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "STORAGETYPE TEXT," +
                "DIMENINSQUAREMETTERS TEXT," +
                "DATETIME TEXT," +
                "STORAGEFEATURE TEXT," +
                "MOUNTHLYRENTPRICE TEXT," +
                "NAMEOFREPORTER TEXT," +
                "NOTE TEXT) ";
        db.execSQL(STORAGE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public SQLiteDatabase open() {
        return this.getWritableDatabase();
    }
}
