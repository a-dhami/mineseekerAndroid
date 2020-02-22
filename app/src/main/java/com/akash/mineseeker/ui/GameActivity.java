package com.akash.mineseeker.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.akash.mineseeker.model.Game;
import com.akash.mineseeker.model.GameManager;
import com.akash.mineseeker.R;

public class GameActivity extends AppCompatActivity {

    GameManager manager = GameManager.getInstance();

    Button buttons[][] = new Button[manager.getRowVal()][manager.getColVal()];

    Game game; //creation of new game

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        game = new Game();
        game.newGame(); //game new initialization

        //increment number of plays
        manager.upNumPlays();

        SharedPreferences sharedPref = getSharedPreferences("GameData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("NumberPlays", manager.getNumPlays());
        editor.putInt("Mines", manager.getMineVal());
        editor.putInt("MapRow", manager.getRowVal());
        editor.putInt("MapCol", manager.getColVal());
        editor.commit();

        populateMines();

        updateMinesVal(); //initialize to 0

        updateScanVal(); //initialize to 0

        Button backMain = findViewById(R.id.btn_gameBack2);
        backMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    public void onBackPressed(){
        finish();
    }

    private void populateMines() {

        TableLayout mineTable = findViewById(R.id.layout_mines);


        for (int row = 0; row < game.getMaxRows(); row++){
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f
            ));
            mineTable.addView(tableRow);


            for(int col = 0; col < game.getMaxCols(); col++){

                final int FINAL_ROW = row;
                final int FINAL_COL = col;

                //button creation
                final Button button = new Button(this);
                button.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f
                ));
                button.setPadding(0,0,0,0);
                button.setTextSize(TypedValue.COMPLEX_UNIT_SP,24);

                buttons[row][col] = button; //set button in button array

                //button listener setup
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(game.isMine(FINAL_ROW,FINAL_COL) == 1)
                        {
                            //change button image to mine image if button is a mine
                            lockButtonSizes();
                            int newWidth = button.getWidth();
                            int newHeight = button.getHeight();
                            Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.krabby_patty);
                            Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap,newWidth, newHeight, true);
                            Resources resource = getResources();
                            button.setBackground(new BitmapDrawable(resource, scaledBitmap));

                            //update mines found
                            updateMinesVal();
                            updateMineScanVal(FINAL_ROW,FINAL_COL);


                        }
                        else if(button.getText().length() == 0){
                            if(game.isMine(FINAL_ROW,FINAL_COL) == 2){
                                button.setTextColor(Color.WHITE);
                            }
                            button.setText(Integer.toString(game.getNearMines(FINAL_ROW,FINAL_COL)));
                            updateScanVal();
                            scanAnimate();
                        }
                    }
                });

                tableRow.addView(button);
            }
        }
    }

    private void scanAnimate() {
        ImageView scanLine = findViewById(R.id.imgView_scan);
        int width = this.getWindow().getDecorView().getWidth();
        int height = this.getWindow().getDecorView().getHeight();
        Animation a = new TranslateAnimation(height,width/2,0,width/2);
        a.setDuration(500);
        scanLine.setVisibility(View.VISIBLE);
        scanLine.startAnimation(a);
        scanLine.setVisibility(View.INVISIBLE);
    }

    private void updateScanVal() {
        game.upScanNum();
        TextView scanText = findViewById(R.id.txtview_scanVal);
        scanText.setText(Integer.toString(game.getScanNum()));
    }

    private void updateMineScanVal(int row, int col) {

        for(int i = 0; i < game.getMaxRows(); i++)
        {
            if(buttons[i][col].getText().length() != 0)  { //only return mines that have not been found
                Button button = buttons[i][col];
                button.setText(Integer.toString(game.getNearMines(i,col)));
            }
        }
        //scan column
        for(int i = 0; i < game.getMaxCols(); i++){
            if(buttons[row][i].getText().length() != 0)  { //only return mines that have not been found
                Button button = buttons[row][i];
                button.setText(Integer.toString(game.getNearMines(row,i)));
            }
        }
    }

    private void updateMinesVal() {
        game.upMinesFounds();
        TextView minesText = findViewById(R.id.txtview_minesFoundVal);
        minesText.setText(game.getMinesFounds() + " out of " + game.getMaxMines());


        //if all mines are found
        if(game.getMinesFounds() == game.getMaxMines()){
            ConstraintLayout congratsLayout = findViewById(R.id.const_congrats);
            congratsLayout.setVisibility(View.VISIBLE);
            Button backButton = findViewById(R.id.btn_gameBack);
            backButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    manager.setNumPlays(manager.getNumPlays() + 1);
                    finish();
                }
            });

        }
    }

    private void lockButtonSizes() {
        int rows = game.getMaxRows();
        int cols = game.getMaxCols();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Button button = buttons[row][col];
                int width = button.getWidth();
                int height = button.getHeight();
                button.setMinimumWidth(width);
                button.setMaxWidth(width);
                button.setMinimumHeight(height);
                button.setMaxHeight(height);
            }
        }
    }
}
