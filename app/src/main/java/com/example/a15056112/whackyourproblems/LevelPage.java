package com.example.a15056112.whackyourproblems;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class LevelPage extends AppCompatActivity {
    Button btnInfo, btnBack, btnLevel1, btnLevel2, btnLevel3, btnLevel4, btnLevel5, btnLevel6, btnLevel7, btnLevel8,
    btnLevel9, btnLevel10, btnLevel11, btnLevel12, btnLevel13;

    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_page);
        getSupportActionBar().hide();

        final MediaPlayer clickSound = MediaPlayer.create(this, R.raw.click2);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        btnBack = (Button) findViewById(R.id.buttonBack);
        btnInfo = (Button) findViewById(R.id.buttonInfo);
        btnLevel1 = (Button) findViewById(R.id.button1);
        btnLevel2 = (Button) findViewById(R.id.button2);
        btnLevel3 = (Button) findViewById(R.id.button3);
        btnLevel4 = (Button) findViewById(R.id.button4);
        btnLevel5 = (Button) findViewById(R.id.button5);
        btnLevel6 = (Button) findViewById(R.id.button6);
        btnLevel7 = (Button) findViewById(R.id.button7);
        btnLevel8 = (Button) findViewById(R.id.button8);
        btnLevel9 = (Button) findViewById(R.id.button9);
        btnLevel10 = (Button) findViewById(R.id.button10);
        btnLevel11 = (Button) findViewById(R.id.button11);
        btnLevel12 = (Button) findViewById(R.id.button12);
        btnLevel13 = (Button) findViewById(R.id.button13);



        btnLevel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSound.start();
                Intent intent = new Intent(LevelPage.this, Level1.class);
                startActivity(intent);
            }
        });

        Intent i = getIntent();
        score = i.getIntExtra("SCORE", 5);
        if (score == 5) {
        btnLevel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Log.e("LevelActivity", "Value of score: " + score);
                    clickSound.start();
                    Intent intent = new Intent(LevelPage.this, Level2.class);
                    startActivity(intent);
                    btnLevel2.setEnabled(true);
                }
            });

        } else {
            btnLevel2.setEnabled(false);
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSound.start();
                finish();
            }
        });

        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSound.start();
                Intent intent = new Intent(LevelPage.this, InfoPage.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        finish();
    }



}
