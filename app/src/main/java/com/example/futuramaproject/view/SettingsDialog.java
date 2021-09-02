package com.example.futuramaproject.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.preference.PreferenceManager;


import com.example.futuramaproject.databinding.SettingsDialogBinding;
import com.example.futuramaproject.service.FuturamaService;

import java.util.concurrent.atomic.AtomicBoolean;


public class SettingsDialog extends DialogFragment {

    private SettingsDialogBinding binding;
    private SharedPreferences preferences;

    private int brightness;
    private boolean isMusicChecked;

    private final String BRIGHTNESS_TAG = "Brightness";
    private final String MUSIC_TAG = "Music";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = SettingsDialogBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        askPermission(getContext());

        saveChanges();
        onBackClick();

        showCurrentState();

        return view;
    }


    private void onBackClick() {
        binding.backButton.setOnClickListener(view -> {
            saveChanges();
            dismiss();
        });
    }

    private void saveChanges(){
        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor editor = preferences.edit();
        //save brightness
        editor.putInt(BRIGHTNESS_TAG, changeBrightness());

        //save sound
        editor.putBoolean(MUSIC_TAG, turnSound());
        editor.commit();
    }

    private void showCurrentState() {
        preferences.getInt(BRIGHTNESS_TAG, 50);
        preferences.getBoolean(MUSIC_TAG, true);
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

    public boolean turnSound() {
        binding.soundSwitch.setOnCheckedChangeListener((compoundButton, isChecked) -> {

            if (!isChecked) {
                getActivity().stopService(new Intent(getContext(), FuturamaService.class));

            } else {
                getActivity().startService(new Intent(getContext(), FuturamaService.class));
            }

            isMusicChecked = isChecked;

        });

        return isMusicChecked;
    }
}
