package com.akash.mineseeker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Scanner;

public class OptionsActivity extends AppCompatActivity {

    GameManager manager = GameManager.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_options);

        setBoardSizeSelector();

        setNumberMineSelector();

        setErasePlaysButton();

        setBackButton();







    }

    private void setErasePlaysButton() {
        Button eraseButton = findViewById(R.id.btn_erasePlays);
        eraseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    private void setNumberMineSelector() {
        Spinner mineSpinner = (Spinner) findViewById(R.id.spin_numberMines);
        ArrayAdapter<String> mineAdapter = new ArrayAdapter<String>(OptionsActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.numbermines));
        mineAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mineSpinner.setAdapter(mineAdapter);

        String data = mineSpinner.getSelectedItem().toString();

        //Scanner s = new Scanner(data);
        //manager.setNumMines(s.nextInt());
    }

    private void setBackButton() {

        Button backButton = findViewById(R.id.btn_optionsBack);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setBoardSizeSelector() {
        final Spinner sizeSpinner = (Spinner) findViewById(R.id.spin_boardSize);
        final ArrayAdapter<String> boardSizeAdapter = new ArrayAdapter<String>(OptionsActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.boardsizes));
        sizeSpinner.setAdapter(boardSizeAdapter);

        sizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String data = boardSizeAdapter.getItem(position);
                Scanner s = new Scanner(data);
                int row = s.useDelimiter("[^\\d]+").nextInt();
                int col = s.useDelimiter("[^\\d]+").nextInt();
                manager.setRowVal(row);
                manager.setColVal(col);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
}
