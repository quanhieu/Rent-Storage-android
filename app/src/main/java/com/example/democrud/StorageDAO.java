package com.example.democrud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class StorageDAO {
    SQLiteDatabase database;

    public StorageDAO(Context context) {
        CreateDatabase createDatabase = new CreateDatabase(context);
        database = createDatabase.open();
    }

    public boolean AddStorage(Storage storage) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("STORAGETYPE", storage.getStorage_type());
        contentValues.put("DIMENINSQUAREMETTERS", storage.getDimens_in_square_meters());
        contentValues.put("DATETIME", storage.getDate_time());
        contentValues.put("STORAGEFEATURE", storage.getStorage_feature());
        contentValues.put("MOUNTHLYRENTPRICE", storage.getMounthly_rent_price());
        contentValues.put("NAMEOFREPORTER", storage.getName_of_reporter());
        contentValues.put("NOTE", storage.getNote());
        long check = database.insert("STORAGE", null, contentValues);
        if (check != 0) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Storage> GetStorageArrayList() {
        ArrayList<Storage> storageArrayList = new ArrayList<>();
        String query = "SELECT * FROM STORAGE";
        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Storage storage = new Storage();
            storage.setId(cursor.getInt(cursor.getColumnIndex("ID")));
            storage.setStorage_type(cursor.getString(cursor.getColumnIndex("STORAGETYPE")));
            storage.setDimens_in_square_meters(cursor.getString(cursor.getColumnIndex("DIMENINSQUAREMETTERS")));
            storage.setDate_time(cursor.getString(cursor.getColumnIndex("DATETIME")));
            storage.setStorage_feature(cursor.getString(cursor.getColumnIndex("STORAGEFEATURE")));
            storage.setMounthly_rent_price(cursor.getString(cursor.getColumnIndex("MOUNTHLYRENTPRICE")));
            storage.setName_of_reporter(cursor.getString(cursor.getColumnIndex("NAMEOFREPORTER")));
            storage.setNote(cursor.getString(cursor.getColumnIndex("NOTE")));
            storageArrayList.add(storage);
            cursor.moveToNext();
        }
        return storageArrayList;
    }

    public boolean UpdateStorage(int id, Storage storage) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("STORAGETYPE", storage.getStorage_type());
        contentValues.put("DIMENINSQUAREMETTERS", storage.getDimens_in_square_meters());
        contentValues.put("DATETIME", storage.getDate_time());
        contentValues.put("STORAGEFEATURE", storage.getStorage_feature());
        contentValues.put("MOUNTHLYRENTPRICE", storage.getMounthly_rent_price());
        contentValues.put("NAMEOFREPORTER", storage.getName_of_reporter());
        contentValues.put("NOTE", storage.getNote());
        long check = database.update("STORAGE", contentValues, "ID = " + id, null);
        if (check != 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean DeleteStorage(int id) {
        long check = database.delete("STORAGE", "ID = " + id, null);
        if (check == 0) {
            return false;
        } else {
            return true;
        }
    }
}
