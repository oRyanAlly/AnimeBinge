package com.animebinge.rally0565.animebinge;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.media.Image;
import android.widget.EditText;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/**
 * Created by Ryan on 2017-09-25.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String tag = "DatabaseHelper";

    private static final String database_name = "Anime.db";
    private static final int database_version = 1;

    private static final String tableName = "members";
    private static final String col2 = "name";
    private static final String col3 = "email";
    private static final String col4 = "password";

    private static final String animeTable = "Anime";
    private static final String episodeURLSTbl = "Episodes";

    public DatabaseHelper(Context context) {
        super(context, database_name, null, database_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createMembersTbl = "CREATE TABLE " + tableName + " (ID INTEGER PRIMARY KEY " +
                "AUTOINCREMENT, " + col2 + " VARCHAR(255), " + col3 + " VARCHAR(255), " +
                col4 + " VARCHAR(255)) ";

        String createAnimeTbl = "CREATE TABLE " + animeTable + " (ID INTEGER PRIMARY KEY " +
                "AUTOINCREMENT, " + "image BLOB, name VARCHAR, avgScore VARCHAR(255), " +
                "type VARCHAR(255), status VARCHAR(255), eps VARCHAR(255), " +
                " aired VARCHAR(255), age VARCHAR(255), desc VARCHAR)";

        String episodeURLsTbl = "CREATE TABLE " + episodeURLSTbl + " (ID INTEGER PRIMARY KEY " +
                "AUTOINCREMENT, " + "animeName VARCHAR(255), episodeNum INTEGER, url VARCHAR(255));";
        db.execSQL(createMembersTbl);
        db.execSQL(createAnimeTbl);
        db.execSQL(episodeURLsTbl);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + tableName);
        db.execSQL("DROP TABLE IF EXISTS " + animeTable);
        db.execSQL("DROP TABLE IF EXISTS " + episodeURLSTbl);
        onCreate(db);
    }

    public boolean addEmailandPass(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col3, email);
        contentValues.put(col4, password);

        long result = db.insert(tableName, null, contentValues);
        db.close();
        //If data is not inserted, it will return -1
        if(result == -1) {
            return false;
        } else {
            return true;
        }
    }
    public Cursor getMember(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
         String query = "SELECT * FROM " + tableName +
        " WHERE " + col3  + " = '" + email + "'";
        Cursor data = db.rawQuery(query,  null);
        if(data != null) {
            data.moveToFirst();
        }
        return data;
    }
    public Cursor getAnime(int id) {
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            String query = "SELECT * FROM " + animeTable +
                    " WHERE ID = '" + id + "'";
            Cursor data = db.rawQuery(query, null);
            if (data != null) {
                data.moveToFirst();
            }
            return data;

        } catch (Exception e) {
            return null;
        }
    }
    public void insertAnime(byte[] image, String name, String avgScore, String type, String status,
                            String eps, String aired, String age, String desc) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("image", image);
        contentValues.put("name", name);
        contentValues.put("avgScore", avgScore);
        contentValues.put("type", type);
        contentValues.put("status", status);
        contentValues.put("eps", eps);
        contentValues.put("aired", aired);
        contentValues.put("age", age);
        contentValues.put("desc", desc);
        db.insert(animeTable, null, contentValues);
    }

    public ArrayList<AnimeShow> getAnimes() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + animeTable;
        ArrayList<AnimeShow> animes = new ArrayList<>();
        Cursor data = db.rawQuery(query, null);
        //https://stackoverflow.com/questions/27818786/fetch-data-from-sqlite-and-display-in-gridview
        if(data != null) {
            while(data.moveToNext()) {
                int id = data.getInt(data.getColumnIndex("ID"));
                byte[] image = data.getBlob(data.getColumnIndex("image"));
                String name = data.getString(data.getColumnIndex("name"));
                String avgScore = data.getString(data.getColumnIndex("avgScore"));
                String type = data.getString(data.getColumnIndex("type"));
                String status = data.getString(data.getColumnIndex("status"));
                String eps = data.getString(data.getColumnIndex("eps"));
                String aired = data.getString(data.getColumnIndex("aired"));
                String age = data.getString(data.getColumnIndex("age"));
                String desc = data.getString(data.getColumnIndex("desc"));

                AnimeShow animeShow = new AnimeShow();
                animeShow.setId(id);
                animeShow.setImage(image);
                animeShow.setName(name);
                animeShow.setAvgScore(avgScore);
                animeShow.setType(type);
                animeShow.setStatus(status);
                animeShow.setEps(eps);
                animeShow.setAired(aired);
                animeShow.setAge(age);
                animeShow.setDesc(desc);
                animes.add(animeShow);
            }
        }
        return animes;
    }
    public boolean addEpisodeURL (String animeName, int nEpisodeNum, String episodeURL) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("animeName", animeName);
        contentValues.put("episodeNum", nEpisodeNum);
        contentValues.put("url", episodeURL);

        long result = db.insert(episodeURLSTbl, null, contentValues);
        db.close();
        //If data is not inserted, it will return -1
        if(result == -1) {
            return false;
        } else {
            return true;
        }
    }
    public Cursor getEpisode (int episodeNum) {
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            String query = "SELECT * FROM " + episodeURLSTbl +
                    " WHERE episodeNum = '" + episodeNum + "'";
            Cursor data = db.rawQuery(query, null);
            if (data != null) {
                data.moveToFirst();
            }
            return data;

        } catch (Exception e) {
            return null;
        }
    }
    public Cursor findAnime (String animeName) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + animeTable +
                " WHERE name = '" +  animeName + "'";

        Cursor data = db.rawQuery(query, null);
        if (data != null) {
            data.moveToFirst();
        }
        return data;
    }
}
