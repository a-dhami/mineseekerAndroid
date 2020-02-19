package com.akash.mineseeker;
//this code is responsible for ensuring that the game data is retained between screens.

public class GameManager {

    private int rowVal; //stores the selected number of rows chosen for game
    private int colVal; //stores the selected number of columns chosen for the game
    private int numMines; //stores the selected number of mines chosen for the game
    private int numPlays; //stores the number of times the game has been played
    private int boardVal; //stores the selected option for the board list
    private int mineVal; //stored the selected option for the mines list


    private static GameManager instance;

    //singleton support for keeping game data constant
    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
            // default number of rows and columns to 4 and 6
            instance.setRowVal(4);
            instance.setColVal(6);
            instance.setNumMines(6);
            instance.setNumPlays(0);
            instance.setBoardVal(0);
            instance.setMineVal(0);
        }
        return instance;
    }

    public int getRowVal() {
        return rowVal;
    }

    public void setRowVal(int rowVal) {
        this.rowVal = rowVal;
    }

    public int getColVal() {
        return colVal;
    }

    public void setColVal(int colVal) {
        this.colVal = colVal;
    }

    public int getNumMines() {
        return numMines;
    }

    public void setNumMines(int numMines) {
        this.numMines = numMines;
    }

    public int getNumPlays() {
        return numPlays;
    }

    public void setNumPlays(int numPlays) {
        this.numPlays = numPlays;
    }

    public int getBoardVal() {
        return boardVal;
    }

    public void setBoardVal(int boardVal) {
        this.boardVal = boardVal;
    }

    public int getMineVal() {
        return mineVal;
    }

    public void setMineVal(int mineVal) {
        this.mineVal = mineVal;
    }
}

