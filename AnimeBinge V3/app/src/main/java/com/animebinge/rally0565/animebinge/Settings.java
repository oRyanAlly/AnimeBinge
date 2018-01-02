package com.animebinge.rally0565.animebinge;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Settings extends AppCompatActivity implements View.OnClickListener {

    Button btnCrunchy, btnFunimation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        btnCrunchy = findViewById(R.id.btnCrunchy);
        btnFunimation = findViewById(R.id.btnFunimation);
        Toolbar tbMenu = (Toolbar) findViewById(R.id.tbMenu);
        setSupportActionBar(tbMenu);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        btnCrunchy.setOnClickListener(this);
        btnFunimation.setOnClickListener(this);
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
                Intent iSettings = new Intent(Settings.this, Settings.class);
                startActivity(iSettings);
                return true;
            case R.id.action_home:
                Intent iHomePage = new Intent(Settings.this, HomePage.class);
                startActivity(iHomePage);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    //https://stackoverflow.com/questions/11753000/how-to-open-the-google-play-store-directly
    // -from-my-android-application
    @Override
    public void onClick(View v) {
        if(btnCrunchy.getId() == v.getId()) {
            Intent iCrunchyRoll = new Intent(Intent.ACTION_VIEW);
            iCrunchyRoll.setData(Uri.parse("market://details?id=com.crunchyroll.crunchyroid"));
            startActivity(iCrunchyRoll);
        }
        if(btnFunimation.getId() == v.getId()) {
            Intent iFunimation = new Intent(Intent.ACTION_VIEW);
            iFunimation.setData(Uri.parse("market://details?id=com.Funimation.FunimationNow"));
            startActivity(iFunimation);
        }
    }
}
