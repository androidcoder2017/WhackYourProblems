package com.example.a15056112.whackyourproblems;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InfoPage extends AppCompatActivity implements View.OnClickListener {
    TextView tvRule1, tvRule2, tvRule3, tvRule4;
    Button btnBack, btnVolume;

    boolean isPreseed = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_page);
        getSupportActionBar().hide();

        final MediaPlayer clickSound = MediaPlayer.create(this, R.raw.click2);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        btnBack = (Button) findViewById(R.id.buttonBack);
        tvRule1 = (TextView) findViewById(R.id.textViewRule1);
        tvRule2 = (TextView)findViewById(R.id.textViewRule2);
        tvRule3 = (TextView)findViewById(R.id.textViewRule3);
        tvRule4 = (TextView)findViewById(R.id.textViewRule4);
        btnVolume = (Button)findViewById(R.id.buttonVolume);

        tvRule1.setText(Html.fromHtml("<p>Hit the character that appears from the hole.</p>"));
        tvRule2.setText(Html.fromHtml("<p>Aim to score the required points to win and unlock the next level.</p>"));
        tvRule3.setText(Html.fromHtml("<p>The game is over when time is up or lives reach 0.</p>"));
        tvRule4.setText(Html.fromHtml("<p>Take note that the countdown timer, lives and score requirement will be set differently " +
                "on each or some level.</p>"));

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSound.start();
                InfoPage.super.onBackPressed();
            }
        });

        btnVolume.setBackgroundResource(R.drawable.mutevolume);
        btnVolume.setOnClickListener(this);

    }

    @Override
    public void onBackPressed() {
        InfoPage.super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        if (isPreseed) {
            btnVolume.setBackgroundResource(R.drawable.mutevolume);
            Intent intent = new Intent(InfoPage.this, MusicService.class);
            stopService(intent);

        } else {
            btnVolume.setBackgroundResource(R.drawable.maxvolume);
            Intent intent = new Intent(InfoPage.this, MusicService.class);
            startService(intent);


        }
        isPreseed = ! isPreseed;
    }
}
