package com.akash.mineseeker.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.akash.mineseeker.model.GameManager;
import com.akash.mineseeker.R;

public class MenuActivity extends AppCompatActivity {

    private GameManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu);

        manager = GameManager.getInstance();

        //saving data between execution
        SharedPreferences sharedPref = getSharedPreferences("GameData", MODE_PRIVATE);
        int numberPlays = sharedPref.getInt("NumberPlays", 0);
        int mines = sharedPref.getInt("Mines", 6);
        int mapRow = sharedPref.getInt("MapRow", 4);
        int mapCol = sharedPref.getInt("MapCol", 6);
        if(mines >= 6)
        {
            manager.setNumMines(mines);
        }
        manager.setNumPlays(numberPlays);
        manager.setRowVal(mapRow);
        manager.setColVal(mapCol);



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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

}


