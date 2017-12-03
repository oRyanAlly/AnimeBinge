package com.animebinge.rally0565.animebinge;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

/**
 * Created by Ryan on 2017-11-30.
 */

public class AnimeVideo extends AppCompatActivity {

    VideoView vvAnimeShow;
    DatabaseHelper dhHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime_video);

        vvAnimeShow = findViewById(R.id.animeVideo);
        Intent pastedIntent = getIntent();
        int nEpisode = pastedIntent.getIntExtra("episode", 0);
        nEpisode = nEpisode + 1;
        dhHelper = new DatabaseHelper(this);
        Cursor episode = dhHelper.getEpisode(nEpisode);

        String videoUrl = episode.getString(3);
        Uri uri = Uri.parse(videoUrl);
        vvAnimeShow.setVideoURI(uri);
        vvAnimeShow.start();

    }
}
