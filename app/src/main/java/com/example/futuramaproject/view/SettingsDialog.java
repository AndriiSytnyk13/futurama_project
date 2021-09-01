package com.example.futuramaproject.view;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.preference.PreferenceManager;


import com.example.futuramaproject.databinding.SettingsDialogBinding;


public class SettingsDialog extends Dialog {

    private SettingsDialogBinding binding;
    private SharedPreferences preferences;
    private int brightness;
    private final String brightnessTag = "Brightness";


    public SettingsDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = SettingsDialogBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        askPermission(getContext());

        saveChanges();
        onBackClick();

        showCurrentState();
    }


    private void onBackClick() {
        binding.backButton.setOnClickListener(view -> {
            dismiss();
        });
    }

    private void saveChanges(){
        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(brightnessTag, changeBrightness());
        editor.apply();
    }

    private void showCurrentState() {
        preferences.getInt(brightnessTag, 50);
    }



    //asks permission to adjust brightness
    private void askPermission(Context c){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(Settings.System.canWrite(c)){
            } else {
                Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                c.startActivity(intent);
            }
        }
    }


    private int changeBrightness() {
        binding.brightnessBar.setMax(100);
        binding.brightnessBar.setKeyProgressIncrement(1);

        try {
            brightness = Settings.System.getInt(getContext().getContentResolver(),
                    Settings.System.SCREEN_BRIGHTNESS);
        } catch (Exception e) {
            e.printStackTrace();

        }

        binding.brightnessBar.setProgress(brightness);

        binding.brightnessBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                brightness = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Settings.System.putInt(getContext().getContentResolver(), Settings.System.SCREEN_BRIGHTNESS
                        , brightness);
            }
        });
        return brightness;
    }
}
