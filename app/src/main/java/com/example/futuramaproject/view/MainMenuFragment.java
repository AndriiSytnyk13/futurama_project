package com.example.futuramaproject.view;

import android.app.Dialog;
import android.app.DialogFragment;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.futuramaproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainMenuFragment extends Fragment {

    @BindView(R.id.settings)
    Button settingsButton;

    @BindView(R.id.about)
    Button aboutButton;

    @BindView(R.id.quit)
    Button quitButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main_menu, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onSettingsClick();
        onAboutClick();
        onQuitClick();
    }

    private void onSettingsClick() {
        settingsButton.setOnClickListener(view -> {
            SettingsDialog settingsDialog = new SettingsDialog(getContext());
            settingsDialog.show();
        });
    }
    
    private void onAboutClick() {
        aboutButton.setOnClickListener(view ->
                Navigation.findNavController(view).navigate(R.id.action_mainMenuFragment_to_aboutFragment));
    }

    private void onQuitClick() {
        quitButton.setOnClickListener(view -> {
            getActivity().finish();
            System.exit(0);
        });
    }
}