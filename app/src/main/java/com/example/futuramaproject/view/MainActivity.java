package com.example.futuramaproject.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import android.media.MediaPlayer;
import android.os.Bundle;
import com.example.futuramaproject.R;


public class MainActivity extends AppCompatActivity {

    private MediaPlayer futuramaThemeSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //creating a background music
        futuramaThemeSong = MediaPlayer.create(this, R.raw.futurama_theme_song);
        futuramaThemeSong.setLooping(true);
        futuramaThemeSong.start();
    }
    @Override
    public boolean onSupportNavigateUp() {
        return Navigation.findNavController(this,R.id.nav_host_fragment).navigateUp();
    }

}