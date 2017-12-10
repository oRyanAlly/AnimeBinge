package com.animebinge.rally0565.animebinge;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.ArrayList;

/**
 * Created by Ryan on 2017-11-30.
 */

public class AnimeVideo extends AppCompatActivity implements View.OnClickListener {

    VideoView vvAnimeShow;
    TextView animeTitle, animeEpisodeNum;
    DatabaseHelper dhHelper;
    MediaController mediaController;
    ProgressBar progressBar;
    ImageButton ibtnNext, ibtnEpisodes;
    int nEpisode, nEpisodes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime_video);

        vvAnimeShow = findViewById(R.id.animeVideo);
        animeTitle = findViewById(R.id.animeNameEpisode);
        animeEpisodeNum = findViewById(R.id.animeEpisode);
        ibtnNext = findViewById(R.id.imgbtnNext);
        ibtnEpisodes = findViewById(R.id.imgbtnEpisodes);

        ibtnNext.setOnClickListener(this);
        ibtnEpisodes.setOnClickListener(new ImageButton.OnClickListener() {
            public void onClick (View v){
            Intent iGoBack = new Intent(AnimeVideo.this, AnimePage.class);
            startActivity(iGoBack);
            }
        });
        Intent pastedIntent = getIntent();
        nEpisode = pastedIntent.getIntExtra("episode", 0);
        nEpisode = nEpisode + 1;
        dhHelper = new DatabaseHelper(this);
        Cursor episode = dhHelper.getEpisode(nEpisode);
            Cursor anime = dhHelper.findAnime(episode.getString(1));
            nEpisodes = Integer.parseInt(anime.getString(6));
            String sAnimeTitle = episode.getString(1);
            String sEpisode = episode.getString(2);
            animeTitle.setText(sAnimeTitle);
            animeEpisodeNum.setText("Episode " + sEpisode);
            mediaController = new MediaController(this);
            String videoUrl = episode.getString(3);
            Uri uri = Uri.parse(videoUrl);
            vvAnimeShow.setVideoURI(uri);
            vvAnimeShow.setMediaController(mediaController);
            mediaController.setAnchorView(vvAnimeShow);
            vvAnimeShow.start();
            Cursor nextEpisode = dhHelper.getEpisode(nEpisode + 1);

            if(nextEpisode.getCount() == 0) {
                ibtnNext.setVisibility(View.INVISIBLE);
            }
        }
    @Override
    public void onClick(View v) {
        if (nEpisode != nEpisodes) {
            Intent iNextPage = new Intent(AnimeVideo.this, AnimeVideo.class);
            iNextPage.putExtra("episode", nEpisode);
            startActivity(iNextPage);
        }
    }
}
