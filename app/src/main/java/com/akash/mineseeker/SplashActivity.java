package com.akash.mineseeker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Button skipButton = findViewById(R.id.btn_splashSkip);
        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SplashActivity.this, MenuActivity.class);
                startActivity(i);
                finish();
            }
        });

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, MenuActivity.class);
                startActivity(i);
                finish();
            }
        }, 5000);

        }
    }
