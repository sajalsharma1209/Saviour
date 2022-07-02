package com.example.saviour.Main_Activity.Menu_Instruction;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.saviour.R;

import java.util.Objects;

public class instructions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);
        Objects.requireNonNull(getSupportActionBar()).hide();
    }
}