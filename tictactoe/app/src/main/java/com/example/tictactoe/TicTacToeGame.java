package com.example.tictactoe;




public class TicTacToeGame {
    String winners_name;

    public static final int GRID_SIZE = 3;

    // Creates an array of integer values
    private Integer[][] mTicTacToeGrid;

    //Resizes the array using the GRID_SIZE
    public TicTacToeGame() {
        mTicTacToeGrid = new Integer[GRID_SIZE][GRID_SIZE];

    }

    //Initializes the values in the array to 0.
    public void newGame() {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                mTicTacToeGrid[row][col] = 0;
            }
        }
    }

    //Returns a boolean value depending on if a player has already selected the button
    public Boolean isSelected(int row, int col) {
        return mTicTacToeGrid[row][col] != 0;
    }




    //Identifies the turn player and changes the button accordingly
    public void selectGridSpace(int row, int col, Players playerPassThrough) {

        //We handle these checks here before passing them to GameFragment.java to obfuscate
        if(playerPassThrough.getCurrentPlayer().equals(playerPassThrough.getplayerOne())) {
            mTicTacToeGrid[row][col] = 1;
        }
        if(playerPassThrough.getCurrentPlayer().equals(playerPassThrough.getplayerTwo())) {
            mTicTacToeGrid[row][col] = -1;
        }
    }


    //Checks if the array is full leading to a tie
    private boolean isFull() {
        int row, col;
        boolean status = true;
        for ( row = 0; row < GRID_SIZE; row++) {
            for ( col = 0; col < GRID_SIZE; col++) {
                if (mTicTacToeGrid[row][col] == 0) {
                    status = false;
                }
            }
        }
        return status;
    }

    //Checks if there is a winner
    public boolean isWinner(Players playerPassThrough){

        //Checking each row
        for (int row = 0; row < GRID_SIZE; row++) {
            int line_total = 0;
            for (int col = 0; col < GRID_SIZE; col++) {
                line_total = line_total +  mTicTacToeGrid[row][col];
                if (line_total == 3 || line_total == -3) {
                    winners_name = playerPassThrough.getCurrentPlayer();
                    return true;
                }
            }
        }

        //Checking each column
        for (int col = 0; col < GRID_SIZE; col++) {
            int line_total = 0;
            for (int row = 0; row < GRID_SIZE; row++) {
                line_total = line_total +  mTicTacToeGrid[row][col];
                if (line_total == 3 || line_total == -3) {
                    winners_name = playerPassThrough.getCurrentPlayer();
                    return true;
                }
            }
        }

        //Checking downward sloping diagonal
        int downwardSumAngle = 0;
        for (int i = 0; i < GRID_SIZE; i++) {
            downwardSumAngle = downwardSumAngle + mTicTacToeGrid[i][i];
        }
        if (downwardSumAngle == -3 || downwardSumAngle == 3) {
            winners_name = playerPassThrough.getCurrentPlayer();
            return true;
        }

        //Checking upward sloping diagonal
        int upwardSumAngle = 0;
        upwardSumAngle = mTicTacToeGrid[0][2] + mTicTacToeGrid[1][1] + mTicTacToeGrid[2][0];
        if (upwardSumAngle == -3 || upwardSumAngle == 3) {
            winners_name = playerPassThrough.getCurrentPlayer();
            return true;
        }
        return false;
    }

    //Handles logic between isWinner() and isFull() to decide when the game is over
    //Called by the onClick button in GameFragment.java
    public boolean isGameOver(Players playerPassThrough) {
        //If the grid is full and there is not a winner: end the game
        if (isFull() && !(isWinner(playerPassThrough))){
            return true;
        }
        //If the there is a winner: end the game
        return isWinner(playerPassThrough);
    }
}
