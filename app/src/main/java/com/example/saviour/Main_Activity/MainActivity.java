package com.example.saviour.Main_Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.saviour.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomnav;

    Home home = new Home();
    Menu menu = new Menu();
    Other_Help help = new Other_Help();

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

        bottomnav = findViewById(R.id.bottom_nav);

        if (new Intent().getBooleanExtra("press", false)) {
            home.send_sms();
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, home).commit();

        bottomnav.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, home).commit();
                    return true;

                case R.id.menu:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, menu).commit();
                    return true;

                case R.id.help:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, help).commit();
                    return true;
            }
            return true;
        });

    }
}