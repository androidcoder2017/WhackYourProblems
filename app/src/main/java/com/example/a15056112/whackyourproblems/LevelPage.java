package com.example.a15056112.whackyourproblems;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;

public class LevelPage extends AppCompatActivity implements View.OnClickListener {
    Button btnInfo, btnBack, btnVolume, btnMute, btnLevel1, btnLevel2, btnLevel3, btnLevel4, btnLevel5, btnLevel6, btnLevel7,
            btnLevel8, btnLevel9, btnLevel10;

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

        btnVolume = (Button)findViewById(R.id.buttonVolume);
        btnMute = (Button)findViewById(R.id.buttonMute);

        final int level1Score = getLevelScore("1");
        final int level2Score = getLevelScore("2");
        final int level3Score = getLevelScore("3");
        final int level4Score = getLevelScore("4");
        final int level5Score = getLevelScore("5");
        final int level6Score = getLevelScore("6");
        final int level7Score = getLevelScore("7");
        final int level8Score = getLevelScore("8");
        final int level9Score = getLevelScore("9");

        btnVolume.setOnClickListener(this);
        btnMute.setOnClickListener(this);

        btnLevel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSound.start();
                Intent intent = new Intent(LevelPage.this, Level1.class);
                startActivity(intent);
            }
        });

        if (level1Score == 25) {
            btnLevel2.setEnabled(true);
        btnLevel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    clickSound.start();
                    Intent intent = new Intent(LevelPage.this, Level2.class);
                    startActivity(intent);

                }
            });

        } else {
            btnLevel2.setEnabled(false);
        }

        if (level2Score == 30) {
            btnLevel3.setEnabled(true);
            btnLevel3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickSound.start();
                    Intent intent = new Intent(LevelPage.this, Level3.class);
                    startActivity(intent);

                }
            });
        } else {
            btnLevel3.setEnabled(false);
        }

        if (level3Score == 35) {
            btnLevel4.setEnabled(true);
            btnLevel4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickSound.start();
                    Intent intent = new Intent(LevelPage.this, Level4.class);
                    startActivity(intent);

                }
            });
        } else {
            btnLevel4.setEnabled(false);
        }

        if (level4Score == 40) {
            btnLevel5.setEnabled(true);
            btnLevel5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickSound.start();
                    Intent intent = new Intent(LevelPage.this, Level5.class);
                    startActivity(intent);

                }
            });
        } else {
            btnLevel5.setEnabled(false);
        }

        if (level5Score == 45) {
            btnLevel6.setEnabled(true);
            btnLevel6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickSound.start();
                    Intent intent = new Intent(LevelPage.this, Level6.class);
                    startActivity(intent);

                }
            });
        } else {
            btnLevel6.setEnabled(false);
        }

        if (level6Score == 50) {
            btnLevel7.setEnabled(true);
            btnLevel7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickSound.start();
                    Intent intent = new Intent(LevelPage.this, Level7.class);
                    startActivity(intent);

                }
            });
        } else {
            btnLevel7.setEnabled(false);
        }

        if (level7Score == 50) {
            btnLevel8.setEnabled(true);
            btnLevel8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickSound.start();
                    Intent intent = new Intent(LevelPage.this, Level8.class);
                    startActivity(intent);

                }
            });
        } else {
            btnLevel8.setEnabled(false);
        }

        if (level8Score == 50) {
            btnLevel9.setEnabled(true);
            btnLevel9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickSound.start();
                    Intent intent = new Intent(LevelPage.this, Level9.class);
                    startActivity(intent);

                }
            });
        } else {
            btnLevel9.setEnabled(false);
        }

        if (level9Score == 50) {
            btnLevel10.setEnabled(true);
            btnLevel10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickSound.start();
                    Intent intent = new Intent(LevelPage.this, Level10.class);
                    startActivity(intent);

                }
            });
        } else {
            btnLevel10.setEnabled(false);
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

    public int getLevelScore(String level) {
        SharedPreferences sharedPref = getSharedPreferences("score", Context.MODE_PRIVATE);
        int levelScore = sharedPref.getInt(level, 0);
        return levelScore;
    }

    @Override
    public void onBackPressed() {
        finish();
    }


    @Override
    public void onClick(View v) {
        if (v == btnVolume) {
            startService(new Intent(this,MusicService.class));
            btnVolume.setEnabled(false);
        } else {
            stopService(new Intent(this,MusicService.class));
            btnVolume.setEnabled(true);
        }
    }
}
