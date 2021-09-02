package com.example.futuramaproject.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import com.example.futuramaproject.R;
import com.example.futuramaproject.service.FuturamaService;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startService(new Intent(this, FuturamaService.class));

    }
    @Override
    public boolean onSupportNavigateUp() {
        return Navigation.findNavController(this,R.id.nav_host_fragment).navigateUp();
    }


}