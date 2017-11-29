package com.animebinge.rally0565.animebinge;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lcodecore.extextview.ExpandTextView;

import java.io.ByteArrayInputStream;

/**
 * Created by Rally0565 on 11/23/2017.
 */

public class AnimePage extends AppCompatActivity {

    DatabaseHelper dhHelper;
    ImageView selectedAnime;
    TextView animeName, tvAvgScore, tvAnimeType, tvStatus, tvEpisodes, tvAired, tvDesc;
    ExpandTextView expandDescription;
    ListView lvEpisodes;
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

        int nNumberofEpisodes = Integer.parseInt(sEpisodes);
        String[] sarEpisodes = new String[nNumberofEpisodes];

        for(int i = 0; i < nNumberofEpisodes; i++) {
            String sNum = Integer.toString(i + 1);
            sarEpisodes[i] = "Episode: " + sNum;
        }
        ArrayAdapter<String> arEpisodes = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, sarEpisodes);
        lvEpisodes.setAdapter(arEpisodes);
    }
}
