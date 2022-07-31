package com.example.saviour.Main_Activity.Menu_Rate_Us;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.saviour.R;

import java.util.Objects;

public class Rate_Us extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        RatingBar ratingBar;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_us);
        setTitle("Rate Us");
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        ratingBar = findViewById(R.id.ratingBar);

        ratingBar.setOnRatingBarChangeListener((ratingBar1, v, b) -> {
            double rate = ratingBar1.getRating();
            Toast.makeText(Rate_Us.this, "" + rate, Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}