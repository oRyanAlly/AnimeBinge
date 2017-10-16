package com.animebinge.rally0565.animebinge;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ryan on 2017-09-25.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String tag = "DatabaseHelper";

    private static final String tableName = "members";
    private static final String col1 = "ID";
    private static final String col2 = "name";
    private static final String col3 = "email";
    private static final String col4 = "password";

    public DatabaseHelper(Context context) {
        super(context, tableName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createMembersTbl = "CREATE TABLE " + tableName + " (ID INTEGER PRIMARY KEY " +
                "AUTOINCREMENT, " + col2 + " VARCHAR(255), " + col3 + " VARCHAR(255), " +
                col4 + " VARCHAR(255)) ";
        db.execSQL(createMembersTbl);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    public boolean addEmailandPass(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col3, email);
        contentValues.put(col4, password);

        long result = db.insert(tableName, null, contentValues);

        //If data is not inserted, it will return -1
        if(result == -1) {
            return false;
        } else {
            return true;
        }
    }
    public Cursor getMember(String email) {
        SQLiteDatabase db= this.getWritableDatabase();
         String query = "SELECT * FROM " + tableName +
        " WHERE " + col3  + " = '" + email + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }
}
