package com.example.a15056112.whackyourproblems;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {
    Button btnExit, btnInfo, btnVolume, btnPlay, btnMute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        final MediaPlayer clickSound = MediaPlayer.create(this, R.raw.click2);



        btnExit = (Button)findViewById(R.id.buttonExit);
        btnInfo = (Button) findViewById(R.id.buttonInfo);
        btnVolume = (Button)findViewById(R.id.buttonVolume);
        btnPlay = (Button) findViewById(R.id.buttonPlay);
        btnMute = (Button)findViewById(R.id.buttonMute);

        btnVolume.setOnClickListener(this);

        btnMute.setOnClickListener(this);


        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSound.start();
                Intent intent = new Intent(MainActivity.this,InfoPage.class);
                startActivity(intent);
            }
        });

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSound.start();
                Intent intent = new Intent(MainActivity.this, LevelPage.class);
                startActivity(intent);
            }
        });


        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSound.start();
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setTitle("Exit");
                builder.setMessage("Are you sure you want to exit?");
                builder.setCancelable(false);
                builder.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        Toast.makeText(MainActivity.this, "Exited", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNeutralButton("Cancel", null);
                builder.show();
            }
        });

    }

    @Override
    public void  onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setTitle("Exit");
        builder.setMessage("Are you sure you want to exit?");
        builder.setCancelable(false);
        builder.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                Toast.makeText(MainActivity.this, "Exited", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNeutralButton("Cancel", null);
        builder.show();

    }

    @Override
    public void finish() {
        stopService(new Intent(this, MusicService.class));
        super.finish();
    }

    @Override
    public void onClick(View v) {
        if (v == btnVolume) {
            startService(new Intent(this, MusicService.class));
            btnVolume.setEnabled(false);
        } else {
            stopService(new Intent(this, MusicService.class));
            btnVolume.setEnabled(true);
        }
    }
}
