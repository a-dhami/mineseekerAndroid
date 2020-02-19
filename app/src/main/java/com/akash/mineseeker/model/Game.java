package com.akash.mineseeker.model;


public class Game{

    private static GameManager manager = GameManager.getInstance();

    private int rows;
    private int cols;
    private int maxMines;
    private int[][] minemap;
    //minemap stores a value of 0 if item is not a mine
    //minemap stores a value of 1 if the item is a mine but has not been found
    //minemap stores a value of 2 if the item is a mine AND has BEEN found.

    private int minesFounds;
    private int scanNum;



    public Game(){
        this.rows = manager.getRowVal();
        this.cols = manager.getColVal();
        this.maxMines = manager.getNumMines();
        this.minemap = new int[this.rows][this.cols];
        this.minesFounds = -1; //set to 0 when GameActivity is opened.
        this.scanNum = -1; //set to 0 when GameActivity is opened.
    }


    public void newGame(){
        for(int i = 0; i < maxMines; i++){

            int randomRow = (int)(rows * Math.random());
            int randomCol = (int)(cols * Math.random());
            if(minemap[randomRow][randomCol] == 1) {
                i--;
            }
            else{
                minemap[randomRow][randomCol] = 1;
            }
        }

    }

    public int isMine(int row, int col){
        if(minemap[row][col] == 1) {
            minemap[row][col] = 2;
            return 1;   //return mine that has not been discovered
        }
        else if(minemap[row][col] == 2){
            return 2;   //return mine that has been discovered
        }
        else{
            return 0;   //return is not mine
        }
    }

    public int getNearMines(int row, int col){
        int mineCount = 0;
        //scan row
        for(int i = 0; i < rows; i++)
        {
            if(minemap[i][col] == 1){ //only return mines that have not been found
                mineCount++;
            }
        }
        //scan column
        for(int i = 0; i < cols; i++){
            if(minemap[row][i] == 1){
                mineCount++;
            }
        }
        return mineCount;
    }

    public int getMinesFounds() {
        return minesFounds;
    }

    public void upMinesFounds() {
        this.minesFounds++;
    }

    public int getMaxMines() {
        return maxMines;
    }

    public int getMaxRows() {
        return rows;
    }

    public int getMaxCols() {
        return cols;
    }

    public int getScanNum() {
        return scanNum;
    }

    public void upScanNum() {
        this.scanNum++;
    }
}


