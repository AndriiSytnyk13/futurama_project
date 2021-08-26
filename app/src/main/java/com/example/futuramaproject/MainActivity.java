package com.example.futuramaproject;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer futuramaThemeSong;

    @BindView(R.id.quit)
    Button quit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        futuramaThemeSong = MediaPlayer.create(MainActivity.this, R.raw.futurama_theme_song);
        futuramaThemeSong.setLooping(true);
        futuramaThemeSong.start();
    }

    public void onQuit(View view) {
        finish();
        System.exit(0);
    }
}