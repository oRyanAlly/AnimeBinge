package com.animebinge.rally0565.animebinge;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime_page);

        Intent pastedIntent = getIntent();
        int nPos = pastedIntent.getIntExtra("position", 0);
        nPos = nPos + 1;
        dhHelper = new DatabaseHelper(this);
        Cursor anime = dhHelper.getAnime(nPos);
        AnimeShow animeShow = new AnimeShow();

        byte[] image = anime.getBlob(anime.getColumnIndex("image"));
        String sName = anime.getString(anime.getColumnIndex("name"));
        String sAvgScore = anime.getString(anime.getColumnIndex("avgScore"));
        String sAnimeType = anime.getString(anime.getColumnIndex("type"));
        String sAnimeStatus = anime.getString(anime.getColumnIndex("status"));
        String sEpisodes = anime.getString(anime.getColumnIndex("eps"));
        String sAired = anime.getString(anime.getColumnIndex("aired"));
        String sDesc = anime.getString(anime.getColumnIndex("desc"));

        animeShow.setImage(image);
        selectedAnime = findViewById(R.id.selectedAnime);
        animeName = findViewById(R.id.animeName);
        tvAvgScore = findViewById(R.id.tvAvgScore);
        tvAnimeType = findViewById(R.id.tvAnimeType);
        tvStatus = findViewById(R.id.tvStatus);
        tvEpisodes = findViewById(R.id.tvEpisodes);
        tvAired = findViewById(R.id.tvAired);
        lvEpisodes = findViewById(R.id.lvEpisodes);
        //tvDesc = findViewById(R.id.tvDescription);
        expandDescription = findViewById(R.id.expandDesc);

        expandDescription.setText(sDesc);
        ByteArrayInputStream bImageStream = new ByteArrayInputStream(image);
        Bitmap bmImage = BitmapFactory.decodeStream(bImageStream);
        selectedAnime.setImageBitmap(bmImage);
        animeName.setText(sName);
        tvAvgScore.setText(sAvgScore);
        tvAnimeType.setText(sAnimeType);
        tvStatus.setText(sAnimeStatus);
        tvEpisodes.setText(sEpisodes);
        tvAired.setText(sAired);

        if (sEpisodes.equals("")) {
            sEpisodes = "0";
        }
        int nNumberofEpisodes = Integer.parseInt(sEpisodes);
        String[] sarEpisodes = new String[nNumberofEpisodes];

        for (int i = 0; i < nNumberofEpisodes; i++) {
            String sNum = Integer.toString(i + 1);
            sarEpisodes[i] = "Episode: " + sNum;
        }
        episodeAdapter = new EpisodeAdapter(this, R.drawable.arrow, sarEpisodes);
        lvEpisodes.setAdapter(episodeAdapter);
        lvEpisodes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent iGoToVid = new Intent(AnimePage.this, AnimeVideo.class);
                iGoToVid.putExtra("episode", position);
                addURLS();
                startActivity(iGoToVid);
            }
        });
    }
    public void addURLS() {
        int i = 1;
        String[] sURLs =
                {
                    "https://s66.escdn.co/jg6nwp64kjtu7m7cy2tvmmau3mj" +
                            "tlbr5mcinbf4iuy6hymfuplh2wzpuv23a/v.mp4",
                        "https://s6.escdn.co/jg6nspg4kjtu7m7cy2tvmyk5st7r2herwpbicp4al" +
                                "uiseneqnk3jpzjdkzuq/v.mp4"
                };
        for (String url: sURLs) {
            dhHelper.addEpisodeURL("Akame Ga Kill!", i, url);
            i++;
        }
    }
}
