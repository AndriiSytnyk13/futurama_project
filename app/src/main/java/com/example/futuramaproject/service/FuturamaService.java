package com.example.futuramaproject.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.IBinder;

import com.example.futuramaproject.R;

public class FuturamaService extends Service {

    private MediaPlayer futuramaThemeSong;

    public FuturamaService () {

    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startPlayingThemeSong();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopPlayingThemeSong();
    }

    public void startPlayingThemeSong() {
        futuramaThemeSong = MediaPlayer.create(this, R.raw.futurama_theme_song);
        futuramaThemeSong.setLooping(true);
        futuramaThemeSong.start();
    }

    public void stopPlayingThemeSong() {
        futuramaThemeSong.stop();
    }

}