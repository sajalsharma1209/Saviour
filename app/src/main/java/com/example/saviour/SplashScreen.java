package com.example.saviour;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.saviour.Main_Activity.MainActivity;

import java.util.Objects;

@SuppressLint("CustomSplashScreen")
public class SplashScreen extends AppCompatActivity {
    ImageView imageView;
    TextView title,bottomtitle;
    Animation top,middle,bottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_screen);
        Objects.requireNonNull(getSupportActionBar()).hide();

        imageView=findViewById(R.id.logo);
        title=findViewById(R.id.title);
        bottomtitle=findViewById(R.id.bottomtitle);

        top= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.top);
        middle= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.middle);
        bottom= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bottom);

        imageView.setAnimation(top);
        title.setAnimation(middle);
        bottomtitle.setAnimation(bottom);

        new Handler().postDelayed(() -> {

            startActivity(new Intent(SplashScreen.this, MainActivity.class));
            finish();
        },3000);
    }
}