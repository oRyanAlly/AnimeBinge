package com.animebinge.rally0565.animebinge;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by Rally0565 on 11/23/2017.
 */

public class AnimePage extends AppCompatActivity {

    DatabaseHelper dhHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime_page);

        Intent pastedIntent = getIntent();
        int nPos = pastedIntent.getIntExtra("position", 0);
        Cursor anime = dhHelper.getAnime(nPos);
        Toast.makeText(this, anime.getString(1), Toast.LENGTH_SHORT).show();
    }
}
