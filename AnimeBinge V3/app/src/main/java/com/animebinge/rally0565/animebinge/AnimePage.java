package com.animebinge.rally0565.animebinge;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.lcodecore.extextview.ExpandTextView;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rally0565 on 11/23/2017.
 */

public class AnimePage extends AppCompatActivity {

    DatabaseHelper dhHelper;
    ImageView selectedAnime;
    TextView animeName, tvAvgScore, tvAnimeType, tvStatus, tvEpisodes, tvAired, tvDesc;
    ExpandTextView expandDescription;
    ListView lvEpisodes;
    EpisodeAdapter episodeAdapter;
    RatingBar ratingBar;
    public static String[] sURLs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime_page);

        //Pass the previous position in order to display specific anime
        Intent pastedIntent = getIntent();
        int nPos = pastedIntent.getIntExtra("position", 0);
        nPos = nPos + 1;
        dhHelper = new DatabaseHelper(this);
        Cursor anime = dhHelper.getAnime(nPos);
        AnimeShow animeShow = new AnimeShow();

        // Assign each variable with a value defined within an Animeshow
        byte[] image = anime.getBlob(anime.getColumnIndex("image"));
        String sName = anime.getString(anime.getColumnIndex("name"));
        double dAvgScore = anime.getDouble(anime.getColumnIndex("avgScore"));
        String sAnimeType = anime.getString(anime.getColumnIndex("type"));
        String sAnimeStatus = anime.getString(anime.getColumnIndex("status"));
        String sEpisodes = anime.getString(anime.getColumnIndex("eps"));
        String sAired = anime.getString(anime.getColumnIndex("aired"));
        String sDesc = anime.getString(anime.getColumnIndex("desc"));

        // Connect a Variable with an element specified on the XML.
        animeShow.setImage(image);
        selectedAnime = findViewById(R.id.selectedAnime);
        animeName = findViewById(R.id.animeName);
        tvAnimeType = findViewById(R.id.tvAnimeType);
        tvStatus = findViewById(R.id.tvStatus);
        tvEpisodes = findViewById(R.id.tvEpisodes);
        tvAired = findViewById(R.id.tvAired);
        lvEpisodes = findViewById(R.id.lvEpisodes);
        expandDescription = findViewById(R.id.expandDesc);
        ratingBar = findViewById(R.id.ratingBar);

        Toolbar tbMenu = (Toolbar) findViewById(R.id.tbMenu);
        setSupportActionBar(tbMenu);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        expandDescription.setText(sDesc);
        ByteArrayInputStream bImageStream = new ByteArrayInputStream(image);
        Bitmap bmImage = BitmapFactory.decodeStream(bImageStream);
        selectedAnime.setImageBitmap(bmImage);
        animeName.setText(sName);
        ratingBar.setRating((float)dAvgScore);
        Drawable progress = ratingBar.getProgressDrawable();
        DrawableCompat.setTint(progress, Color.YELLOW);
        tvAnimeType.setText(sAnimeType);
        tvStatus.setText(sAnimeStatus);
        tvEpisodes.setText(sEpisodes);
        tvAired.setText(sAired);
        //Set default value to zero if string is empty or null
        if (sEpisodes.equals("")) {
            sEpisodes = "0";
        }
        int nNumberofEpisodes = Integer.parseInt(sEpisodes);
        String[] sarEpisodes = new String[nNumberofEpisodes];
        //Storing each Episode as "Episode: #" So when displayed it will have the correct amount
        //of episodes
        for (int i = 0; i < nNumberofEpisodes; i++) {
            String sNum = Integer.toString(i + 1);
            sarEpisodes[i] = "Episode: " + sNum;
        }
        // Display listview of episodes and if clicked, display specific episode
        //& pass the episode number so it can grab the appropriate episode
        episodeAdapter = new EpisodeAdapter(this, R.drawable.arrow, sarEpisodes);
        lvEpisodes.setAdapter(episodeAdapter);
        lvEpisodes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                addURLS();
                Cursor episodeExists = dhHelper.getEpisode(position + 1);
                if (episodeExists.getCount() != 0) {
                    Intent iGoToVid = new Intent(AnimePage.this, AnimeVideo.class);
                    iGoToVid.putExtra("episode", position);
                    startActivity(iGoToVid);
                }
                else {
                    Toast.makeText(AnimePage.this, "Episode has not been added",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent iSettings = new Intent(AnimePage.this, Settings.class);
                startActivity(iSettings);
                return true;
            case R.id.action_home:
                Intent iHomePage = new Intent(AnimePage.this, HomePage.class);
                startActivity(iHomePage);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    //Store anime video links for specific animes
    public void addURLS() {
        int i = 1;
        sURLs = new String[] {"http://s7vxx.animeheaven.eu/video/Akame_ga_Kill!--1--14" +
                "71051479.mp4?w3w174",
                        "http://s9y.animeheaven.eu/720pi/Akame_ga_Kill!--2--1471051481.mp4?d3d174"};
        for (String url: sURLs) {
            dhHelper.addEpisodeURL("Akame Ga Kill!", i, url);
            i++;
        }
    }
}
