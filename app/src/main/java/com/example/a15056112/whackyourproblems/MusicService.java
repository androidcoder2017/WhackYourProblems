package com.example.a15056112.whackyourproblems;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by 15056112 on 16/7/2017.
 */

public class MusicService extends Service {

    private MediaPlayer backgroundmusic;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        backgroundmusic = MediaPlayer.create(this,R.raw.musicbackground);
        backgroundmusic.setLooping(true);
        backgroundmusic.start();
        return START_STICKY;

    }



    @Override
    public void onDestroy() {
        backgroundmusic.stop();
        backgroundmusic.release();
    }
}
