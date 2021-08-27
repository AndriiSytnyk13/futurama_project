package com.example.futuramaproject.view;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.futuramaproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainMenuFragment extends Fragment {

    private MediaPlayer futuramaThemeSong;

    @BindView(R.id.quit)
    Button quit;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main_menu, container, false);
        ButterKnife.bind(this, view);
        futuramaThemeSong = MediaPlayer.create(getContext(), R.raw.futurama_theme_song);
        futuramaThemeSong.setLooping(true);
        futuramaThemeSong.start();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onQuit();

    }

    private void onQuit() {
        quit.setOnClickListener(view1 -> {
            getActivity().finish();
            System.exit(0);
        });

    }
}