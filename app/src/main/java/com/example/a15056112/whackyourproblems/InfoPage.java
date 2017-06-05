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

public class InfoPage extends AppCompatActivity {
    TextView tvRule1, tvRule2, tvRule3, tvRule4;
    Button btnBack;


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

        tvRule1.setText(Html.fromHtml("<p>Hit as many obstacles that appeared as possible such as strict teachers and boss to achieve the " +
                "score required to unlock next level.</p>"));
        tvRule2.setText(Html.fromHtml("<p>Countdown timer gets shorter when playing at higher levels.</p>"));
        tvRule3.setText(Html.fromHtml("<p>The game is over when time is up or lives reach 0.</p>"));
        tvRule4.setText(Html.fromHtml("<p>Aim to score more than 30 points to win and unlock the next level.</p>"));

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSound.start();
                InfoPage.super.onBackPressed();
            }
        });

    }

    @Override
    public void onBackPressed() {
        InfoPage.super.onBackPressed();
    }
}
