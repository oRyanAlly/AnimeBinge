package com.animebinge.rally0565.animebinge;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        btnCrunchy.setOnClickListener(this);
        btnFunimation.setOnClickListener(this);
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
