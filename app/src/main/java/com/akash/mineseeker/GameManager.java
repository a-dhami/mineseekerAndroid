package com.akash.mineseeker;

public class GameManager {

    private int rowVal;
    private int colVal;


    private static GameManager instance;

    //singleton support for keeping game data constant
    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
            instance.setRowVal(0);
            instance.setColVal(0);

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
}

