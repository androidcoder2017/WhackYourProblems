package com.example.a15056112.whackyourproblems;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Level5 extends AppCompatActivity implements View.OnClickListener {

    TextView tvLives, tvTime, tvScore;
    Button btnStart, btnBack,btnVolume;
    ImageView ivBoss1,ivBoss2,ivBoss3,ivBoss4,ivBoss5,ivBoss6,ivBoss7,ivBoss8,ivBoss9,ivBoss10,ivBoss11,ivBoss12;
    ImageView ivHole1,ivHole2,ivHole3,ivHole4,ivHole5,ivHole6,ivHole7,ivHole8,ivHole9,ivHole10,ivHole11,ivHole12;

    Random r;

    boolean isPreseed = false;

    int score = 0, fps = 750, left = 8, tempileft = 0;

    int which = 0;
    int whichsave = 0;

    AnimationDrawable an;

    android.os.Handler handler = new android.os.Handler();
    Runnable runnable;

    CountDownTimer runTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level5);

        getSupportActionBar().hide();

        tvLives = (TextView)findViewById(R.id.textViewLives);
        tvTime = (TextView)findViewById(R.id.textViewTimeLimit);
        tvScore = (TextView) findViewById(R.id.textViewScore);
        btnStart = (Button)findViewById(R.id.buttonStart);
        btnBack = (Button)findViewById(R.id.buttonBack);
        btnVolume = (Button)findViewById(R.id.buttonVolume);

        ivBoss1 = (ImageView)findViewById(R.id.imageViewBoss1);
        ivBoss2 = (ImageView)findViewById(R.id.imageViewBoss2);
        ivBoss3 = (ImageView)findViewById(R.id.imageViewBoss3);
        ivBoss4 = (ImageView)findViewById(R.id.imageViewBoss4);
        ivBoss5 = (ImageView)findViewById(R.id.imageViewBoss5);
        ivBoss6 = (ImageView)findViewById(R.id.imageViewBoss6);
        ivBoss7 = (ImageView)findViewById(R.id.imageViewBoss7);
        ivBoss8 = (ImageView)findViewById(R.id.imageViewBoss8);
        ivBoss9 = (ImageView)findViewById(R.id.imageViewBoss9);
        ivBoss10 = (ImageView)findViewById(R.id.imageViewBoss10);
        ivBoss11 = (ImageView)findViewById(R.id.imageViewBoss11);
        ivBoss12 = (ImageView)findViewById(R.id.imageViewBoss12);

        ivHole1 = (ImageView)findViewById(R.id.imageViewHole1);
        ivHole2 = (ImageView)findViewById(R.id.imageViewHole2);
        ivHole3 = (ImageView)findViewById(R.id.imageViewHole3);
        ivHole4 = (ImageView)findViewById(R.id.imageViewHole4);
        ivHole5 = (ImageView)findViewById(R.id.imageViewHole5);
        ivHole6 = (ImageView)findViewById(R.id.imageViewHole6);
        ivHole7 = (ImageView)findViewById(R.id.imageViewHole7);
        ivHole8 = (ImageView)findViewById(R.id.imageViewHole8);
        ivHole9 = (ImageView)findViewById(R.id.imageViewHole9);
        ivHole10 = (ImageView)findViewById(R.id.imageViewHole10);
        ivHole11 = (ImageView)findViewById(R.id.imageViewHole11);
        ivHole12 = (ImageView)findViewById(R.id.imageViewHole12);

        final MediaPlayer whackSound = MediaPlayer.create(this, R.raw.whack);
        final MediaPlayer clickSound = MediaPlayer.create(this, R.raw.click2);
        final MediaPlayer secondRemainingSound = MediaPlayer.create(this, R.raw.secondsremaining);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        btnVolume.setBackgroundResource(R.drawable.mutevolume);
        btnVolume.setOnClickListener(this);

        ivBoss1.setVisibility(View.INVISIBLE);
        ivBoss2.setVisibility(View.INVISIBLE);
        ivBoss3.setVisibility(View.INVISIBLE);
        ivBoss4.setVisibility(View.INVISIBLE);
        ivBoss5.setVisibility(View.INVISIBLE);
        ivBoss6.setVisibility(View.INVISIBLE);
        ivBoss7.setVisibility(View.INVISIBLE);
        ivBoss8.setVisibility(View.INVISIBLE);
        ivBoss9.setVisibility(View.INVISIBLE);
        ivBoss10.setVisibility(View.INVISIBLE);
        ivBoss11.setVisibility(View.INVISIBLE);
        ivBoss12.setVisibility(View.INVISIBLE);

        r = new Random();

        runnable = new Runnable() {
            @Override
            public void run() {
                gamePlay();
            }
        };

        tvLives.setText(" " + left);
        tvScore.setText("Get 45");
        tvTime.setText("Time: 55 sec");

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSound.start();
                left = 8;
                tvLives.setText(" " + left);
                score = 0;
                tvScore.setText(" "  + score);


                handler.postDelayed(runnable,fps);

                btnStart.setEnabled(false);

                runTimer = new CountDownTimer(55000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        String text = String.format(Locale.getDefault(), "%02d min: %02d sec",
                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) % 60, TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) % 60);
                        tvTime.setText(text);

                        if(millisUntilFinished <= 10000) {
                            secondRemainingSound.start();
                            tvTime.setTextColor(Color.RED);
                        }
                    }

                    @Override
                    public void onFinish() {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Level5.this);
                        builder.setTitle("Time's up!");
                        builder.setMessage("Unfortunately, you lost the game! But do not worry, try again till you succeed!");
                        builder.setCancelable(false);
                        builder.setPositiveButton("Back", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });
                        handler.removeCallbacks(runnable);
                        builder.show();
                        runTimer.cancel();
                    }
                }.start();

            }
        });

        ivBoss1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whackSound.start();
                tempileft = 1;
                ivBoss1.setImageResource(R.drawable.bosshit);
                score = score + 1;
                tvScore.setText(" "  + score);
                ivBoss1.setEnabled(false);
            }
        });

        ivBoss2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whackSound.start();
                tempileft = 1;
                ivBoss2.setImageResource(R.drawable.bosshit);
                score = score + 1;
                tvScore.setText(" "  + score);
                ivBoss2.setEnabled(false);
            }
        });

        ivBoss3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whackSound.start();
                tempileft = 1;
                ivBoss3.setImageResource(R.drawable.bosshit);
                score = score + 1;
                tvScore.setText(" "  + score);
                ivBoss3.setEnabled(false);
            }
        });

        ivBoss4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whackSound.start();
                tempileft = 1;
                ivBoss4.setImageResource(R.drawable.bosshit);
                score = score + 1;
                tvScore.setText(" "  + score);
                ivBoss4.setEnabled(false);
            }
        });

        ivBoss5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whackSound.start();
                tempileft = 1;
                ivBoss5.setImageResource(R.drawable.bosshit);
                score = score + 1;
                tvScore.setText(" "  + score);
                ivBoss5.setEnabled(false);
            }
        });

        ivBoss6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whackSound.start();
                tempileft = 1;
                ivBoss6.setImageResource(R.drawable.bosshit);
                score = score + 1;
                tvScore.setText(" "  + score);
                ivBoss6.setEnabled(false);
            }
        });

        ivBoss7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whackSound.start();
                tempileft = 1;
                ivBoss7.setImageResource(R.drawable.bosshit);
                score = score + 1;
                tvScore.setText(" "  + score);
                ivBoss7.setEnabled(false);
            }
        });

        ivBoss8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whackSound.start();
                tempileft = 1;
                ivBoss8.setImageResource(R.drawable.bosshit);
                score = score + 1;
                tvScore.setText(" "  + score);
                ivBoss8.setEnabled(false);
            }
        });

        ivBoss9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whackSound.start();
                tempileft = 1;
                ivBoss9.setImageResource(R.drawable.bosshit);
                score = score + 1;
                tvScore.setText(" "  + score);
                ivBoss9.setEnabled(false);
            }
        });

        ivBoss10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whackSound.start();
                tempileft = 1;
                ivBoss10.setImageResource(R.drawable.bosshit);
                score = score + 1;
                tvScore.setText(" "  + score);
                ivBoss10.setEnabled(false);
            }
        });

        ivBoss11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whackSound.start();
                tempileft = 1;
                ivBoss11.setImageResource(R.drawable.bosshit);
                score = score + 1;
                tvScore.setText(" "  + score);
                ivBoss11.setEnabled(false);
            }
        });

        ivBoss12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whackSound.start();
                tempileft = 1;
                ivBoss12.setImageResource(R.drawable.bosshit);
                score = score + 1;
                tvScore.setText(" "  + score);
                ivBoss12.setEnabled(false);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSound.start();
                AlertDialog.Builder builder = new AlertDialog.Builder(Level5.this);

                builder.setTitle("Exit");
                builder.setMessage("Are you sure you want to exit?");
                builder.setCancelable(false);
                builder.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();

                    }
                });
                builder.setNeutralButton("Cancel", null);
                builder.show();;
            }
        });
    }

    private void gamePlay() {
        an = (AnimationDrawable) ContextCompat.getDrawable(this, R.drawable.anim);

        do {
            which = r.nextInt(12) + 1;
        } while (whichsave == which);
        whichsave = which;

        if(which == 1) {
            ivBoss1.setImageDrawable(an);
            ivBoss1.setVisibility(View.VISIBLE);
            ivBoss1.setEnabled(true);
        } else if(which == 2) {
            ivBoss2.setImageDrawable(an);
            ivBoss2.setVisibility(View.VISIBLE);
            ivBoss2.setEnabled(true);
        } else if(which == 3) {
            ivBoss3.setImageDrawable(an);
            ivBoss3.setVisibility(View.VISIBLE);
            ivBoss3.setEnabled(true);
        } else if(which == 4) {
            ivBoss4.setImageDrawable(an);
            ivBoss4.setVisibility(View.VISIBLE);
            ivBoss4.setEnabled(true);
        } else if(which == 5) {
            ivBoss5.setImageDrawable(an);
            ivBoss5.setVisibility(View.VISIBLE);
            ivBoss5.setEnabled(true);
        } else if(which == 6) {
            ivBoss6.setImageDrawable(an);
            ivBoss6.setVisibility(View.VISIBLE);
            ivBoss6.setEnabled(true);
        } else if(which == 7) {
            ivBoss7.setImageDrawable(an);
            ivBoss7.setVisibility(View.VISIBLE);
            ivBoss7.setEnabled(true);
        } else if(which == 8) {
            ivBoss8.setImageDrawable(an);
            ivBoss8.setVisibility(View.VISIBLE);
            ivBoss8.setEnabled(true);
        } else if(which == 9) {
            ivBoss9.setImageDrawable(an);
            ivBoss9.setVisibility(View.VISIBLE);
            ivBoss9.setEnabled(true);
        } else if(which == 10) {
            ivBoss10.setImageDrawable(an);
            ivBoss10.setVisibility(View.VISIBLE);
            ivBoss10.setEnabled(true);
        } else if(which == 11) {
            ivBoss11.setImageDrawable(an);
            ivBoss11.setVisibility(View.VISIBLE);
            ivBoss11.setEnabled(true);
        } else if(which == 12) {
            ivBoss12.setImageDrawable(an);
            ivBoss12.setVisibility(View.VISIBLE);
            ivBoss12.setEnabled(true);
        }

        an.start();


        runnable = new Runnable() {
            @Override
            public void run() {

                ivBoss1.setVisibility(View.INVISIBLE);
                ivBoss2.setVisibility(View.INVISIBLE);
                ivBoss3.setVisibility(View.INVISIBLE);
                ivBoss4.setVisibility(View.INVISIBLE);
                ivBoss5.setVisibility(View.INVISIBLE);
                ivBoss6.setVisibility(View.INVISIBLE);
                ivBoss7.setVisibility(View.INVISIBLE);
                ivBoss8.setVisibility(View.INVISIBLE);
                ivBoss9.setVisibility(View.INVISIBLE);
                ivBoss10.setVisibility(View.INVISIBLE);
                ivBoss11.setVisibility(View.INVISIBLE);
                ivBoss12.setVisibility(View.INVISIBLE);

                ivBoss1.setEnabled(false);
                ivBoss2.setEnabled(false);
                ivBoss3.setEnabled(false);
                ivBoss4.setEnabled(false);
                ivBoss5.setEnabled(false);
                ivBoss6.setEnabled(false);
                ivBoss7.setEnabled(false);
                ivBoss8.setEnabled(false);
                ivBoss9.setEnabled(false);
                ivBoss10.setEnabled(false);
                ivBoss11.setEnabled(false);
                ivBoss12.setEnabled(false);

                if(tempileft == 0) {
                    left = left - 1;
                    tvLives.setText(" "  + left);
                } else if (tempileft == 1) {
                    tempileft = 0;
                }

                if (left == 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Level5.this);

                    builder.setTitle("Game Over");
                    builder.setMessage("You lost! Don't worry, you can always try again!");
                    builder.setCancelable(false);
                    builder.setPositiveButton("Back", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();

                        }
                    });

                    builder.show();
                    runTimer.cancel();
                } else if (score == 45) {
                    saveData("5", score);
                    AlertDialog.Builder builder = new AlertDialog.Builder(Level5.this);
                    builder.setTitle("Level 5 Complete!");
                    builder.setMessage("You have won! You have unlock level 6!");
                    builder.setPositiveButton("Unlock", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Level5.this, LevelPage.class);
                            startActivity(intent);
                            finish();
                        }
                    });

                    builder.setCancelable(false);
                    builder.show();
                    runTimer.cancel();
                }
                else {
                    gamePlay();
                }


            }
        };

        handler.postDelayed(runnable,fps);

    }

    private void saveData(String level, int score) {
        SharedPreferences pref = this.getSharedPreferences("score", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        edit.putInt(level,score);
        edit.commit();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Level5.this);
        builder.setTitle("Exit");
        builder.setMessage("Are you sure you want to exit?");
        builder.setCancelable(false);
        builder.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNeutralButton("Cancel", null);
        builder.show();
    }

    @Override
    public void onClick(View v) {
        if (isPreseed) {
            btnVolume.setBackgroundResource(R.drawable.mutevolume);
            Intent intent = new Intent(Level5.this, MusicService.class);
            stopService(intent);

        } else {
            btnVolume.setBackgroundResource(R.drawable.maxvolume);
            Intent intent = new Intent(Level5.this, MusicService.class);
            startService(intent);


        }
        isPreseed = ! isPreseed;
    }
}
