package com.akash.mineseeker.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.akash.mineseeker.model.GameManager;
import com.akash.mineseeker.R;

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

                Toast.makeText(getApplicationContext(), "Number of Plays : 0", Toast.LENGTH_SHORT).show();
                manager.setNumPlays(0);
            }
        });
    }

    private void setNumberMineSelector() {
        Spinner mineSpinner = (Spinner) findViewById(R.id.spin_numberMines);
        final ArrayAdapter<String> mineAdapter = new ArrayAdapter<String>(OptionsActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.numbermines));
        mineSpinner.setAdapter(mineAdapter);
        mineSpinner.setSelection(manager.getMineVal());

        mineSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String data = mineAdapter.getItem(position);

                Scanner s = new Scanner(data);
                int mines = s.useDelimiter("[^\\d]+").nextInt();
                manager.setNumMines(mines);
                manager.setMineVal(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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
        sizeSpinner.setSelection(manager.getBoardVal());
        sizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String data = boardSizeAdapter.getItem(position);
                Scanner s = new Scanner(data);
                int row = s.useDelimiter("[^\\d]+").nextInt();
                int col = s.useDelimiter("[^\\d]+").nextInt();
                manager.setRowVal(row);
                manager.setColVal(col);
                manager.setBoardVal(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
}
