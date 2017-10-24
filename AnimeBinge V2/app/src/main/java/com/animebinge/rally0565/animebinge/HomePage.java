package com.animebinge.rally0565.animebinge;

import android.app.ActionBar;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.animation.GridLayoutAnimationController;
import android.widget.GridView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class HomePage extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    GridView gvAnime;
    ArrayList<AnimeShow> animeShows;
    public static String[] animeNames = {
            "Akame Ga Kill",
            "Another",
            "Big Order",
            "Black Bullet",
            "Death Note",
            "Golden Time",
            "Hunter X Hunter",
            "Sword Art Online",
            "Tokyo Ghoul",
            "Toradora!"
    };
    public static int[] animeImages = {
            R.drawable.akamegakill,
            R.drawable.another,
            R.drawable.bigorder,
            R.drawable.blackbullet,
            R.drawable.deathnote,
            R.drawable.goldentime,
            R.drawable.hunterxhunter,
            R.drawable.swordartonline,
            R.drawable.tokyoghoul,
            R.drawable.toradora
    };

    //https://www.androidtutorialpoint.com/material-design/
    // android-custom-gridview-example-image-text/#Android_GridView_Custom_Adapter
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        databaseHelper = new DatabaseHelper(this);
        Cursor anime;
        anime = databaseHelper.getAnime(animeNames[0]);

        if(anime.getCount() == 0 ) {
            addAnime();
        }
        Toolbar tbMenu = (Toolbar) findViewById(R.id.tbMenu);
        setSupportActionBar(tbMenu);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        gvAnime = (GridView) findViewById(R.id.gvAnime);
        gvAnime.setAdapter(new AnimeListAdapter(this, animeNames, animeImages));

        tbMenu.setBackgroundColor(Color.parseColor("#141414"));

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    private void addAnime() {
        //https://stackoverflow.com/questions/13840504/how-to-save-and-retrive-images-from-sql-lite-database-in-android
        //https://stackoverflow.com/questions/15255611/how-to-convert-a-drawable-image-from-resources-to-a-bitmap
        for(int i = 0; i < animeImages.length; i++) {
           Bitmap bitmap = BitmapFactory.decodeResource(getResources(), animeImages[i]);

            ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();

            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteOutput);

            byte[] convertedImage = byteOutput.toByteArray();
            databaseHelper.insertAnime(convertedImage, animeNames[i]);
        }
    }

}
