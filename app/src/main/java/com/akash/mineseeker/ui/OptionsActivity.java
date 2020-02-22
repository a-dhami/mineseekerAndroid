package com.akash.mineseeker.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
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

        TextView numPlay = findViewById(R.id.txtview_numPlay);
        numPlay.setText(Integer.toString(manager.getNumPlays()));

    }

    private void setErasePlaysButton() {
        Button eraseButton = findViewById(R.id.btn_erasePlays);
        eraseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "Number of Plays : 0", Toast.LENGTH_SHORT).show();
                manager.setNumPlays(0);
                TextView numPlay = findViewById(R.id.txtview_numPlay);
                numPlay.setText(Integer.toString(manager.getNumPlays()));
                updatePersistance();
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
                updatePersistance();
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
                updatePersistance();
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
                updatePersistance();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    private void updatePersistance() {
        SharedPreferences sharedPref = getSharedPreferences("GameData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("NumberPlays", manager.getNumPlays());
        editor.putInt("Mines", manager.getMineVal());
        editor.putInt("MapRow", manager.getRowVal());
        editor.putInt("MapCol", manager.getColVal());
        editor.commit();
    }
}
