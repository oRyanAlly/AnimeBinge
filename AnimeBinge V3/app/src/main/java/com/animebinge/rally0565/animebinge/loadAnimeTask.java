package com.animebinge.rally0565.animebinge;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import java.util.ArrayList;

/**
 * Created by Ryan on 2017-12-31.
 */

//https://www.youtube.com/watch?v=u4828hciA-I
public class loadAnimeTask extends AsyncTask<Void, Integer, ArrayList<AnimeShow>> {

    DatabaseHelper databaseHelper;
    ArrayList<AnimeShow> animeShows;
    Context context;
    ProgressDialog progressDialog;
    GridView gvAnime;

    loadAnimeTask(Context context, ArrayList<AnimeShow> animeShows, GridView gvAnime)
        {
        this.context = context;
        this.animeShows = animeShows;
        this.gvAnime = gvAnime;
        }

    @Override
    protected ArrayList<AnimeShow> doInBackground(Void... voids) {
        int i = 0;
        databaseHelper = new DatabaseHelper(context);
        animeShows = databaseHelper.getAnimes();

        synchronized (this) {
            while (i < animeShows.size())
            {
                try {
                    wait(400);
                    i++;
                    publishProgress(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return animeShows;
    }

    @Override
    protected void onPreExecute() {

        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Loading Animes.....");
        progressDialog.setMax(10);
        progressDialog.setProgress(0);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();

    }
    @Override
    protected void onPostExecute(ArrayList<AnimeShow> animeShows) {
        gvAnime.setVisibility(View.VISIBLE);
        progressDialog.dismiss();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        int nProgress = values[0];
        progressDialog.setProgress(nProgress);

    }
}
