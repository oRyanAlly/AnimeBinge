package com.animebinge.rally0565.animebinge;

import android.app.ActionBar;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.animation.GridLayoutAnimationController;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.in;

public class HomePage extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    GridView gvAnime;

    public static ArrayList<AnimeShow> animeShows;
    public static ArrayList<AnimeShow> animes;
    public static String[] animeNames = {
            "Akame Ga Kill!",
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
    public static String[] avgScore = {
            "3.95/5",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };
    public static String[] type = {
            "TV",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };
    public static String[] status = {
            "COMPLETED",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };
    public static String[] eps = {
            "24",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };
    public static String[] aired = {
            "JUL 2014 - DEC 2014",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };
    public static String[] age = {
            "R - 17+ (Violence & Profanity)",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };
    public static String[] animeDesc = {
            "Night Raid is the covert assassination branch of the Revolutionary Army, an uprising " +
                    "assembled to overthrow Prime Minister Honest, whose avarice and greed for " +
                    "power has lead him to take advantage of the child emperor's inexperience. " +
                    "Without a strong and benevolent leader, the rest of the nation is left to " +
                    "drown in poverty, strife, and ruin. Though the Night Raid members are all " +
                    "experienced killers, they understand that taking lives is far from " +
                    "commendable and that they will likely face retribution as they mercilessly " +
                    "eliminate anyone who stands in the revolution's way." +
                    " This merry band of assassins' newest member is Tatsumi, a naïve boy from a " +
                    "remote village who had embarked on a journey to help his impoverished" +
                    " hometown and was won over by not only Night Raid's ideals, but also their " +
                    "resolve. Akame ga Kill! follows Tatsumi as he fights the Empire and comes " +
                    "face-to-face with powerful weapons, enemy assassins, challenges to his own" +
                    " morals and values, and ultimately, what it truly means to be an assassin " +
                    "with a cause.\n" +
                    " ",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
    };
    //https://www.androidtutorialpoint.com/material-design/
    // android-custom-gridview-example-image-text/#Android_GridView_Custom_Adapter
    // Grab to see if any anime shows are in the database, if it is empty, add the shows in.
    // Re-grab to update the array then display the anime shows in the adapter
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        databaseHelper = new DatabaseHelper(this);
        animeShows = databaseHelper.getAnimes();

        if(animeShows.isEmpty()) {
            addAnime();
            animeShows = databaseHelper.getAnimes();
        }
        Toolbar tbMenu = (Toolbar) findViewById(R.id.tbMenu);
        setSupportActionBar(tbMenu);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        gvAnime = (GridView) findViewById(R.id.gvAnime);

        gvAnime.setAdapter(new AnimeListAdapter(this, R.layout.griditem_layout, animeShows));
        gvAnime.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent iClickedAnime = new Intent(HomePage.this, AnimePage.class);
                iClickedAnime.putExtra("position", position);
                startActivity(iClickedAnime);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    // Decrypt each image in the array into a bitmap then cover it into a byte to be stored
    //in the database
    private void addAnime() {
        //https://stackoverflow.com/questions/13840504/how-to-save-and-retrive-images-from-sql-lite-database-in-android
        //https://stackoverflow.com/questions/15255611/how-to-convert-a-drawable-image-from-resources-to-a-bitmap
        for(int i = 0; i < animeImages.length - 1; i++) {
           Bitmap bitmap = BitmapFactory.decodeResource(getResources(), animeImages[i]);

            ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();

            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteOutput);

            byte[] convertedImage = byteOutput.toByteArray();
             databaseHelper.insertAnime(convertedImage, animeNames[i], avgScore[i], type[i],
                    status[i], eps[i], aired[i], age[i], animeDesc[i]);
        }
    }

}
