package com.akash.mineseeker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    private GameManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu);

        manager = GameManager.getInstance();


        Button helpButton = findViewById(R.id.btn_menuHelp);
        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuActivity.this, HelpActivity.class);
                startActivity(i);
            }
        });

        Button optionsButton = findViewById(R.id.btn_menuOptions);
        optionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuActivity.this, OptionsActivity.class);
                startActivity(i);
            }
        });

        Button playButton = findViewById(R.id.btn_menuPlay);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuActivity.this, GameActivity.class);
                startActivity(i);
            }
        });
    }
}
