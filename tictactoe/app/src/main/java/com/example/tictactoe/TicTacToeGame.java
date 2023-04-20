package com.example.tictactoe;

public class TicTacToeGame {

    Players player_construct = new Players("Bob", "Rick");
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

    //TODO - I think I did the constructor correctly. IDK someone review it.
    //Handel and call the player class from this class by calling it when the onStart button is clicked in the HomeFragment
    public static class Players {

        public String PlayerTwo;
        public String PlayerOne;
        public String CurrentPlayer;

        public Players(String PlayerOne, String PlayerTwo) {
            this.PlayerOne = PlayerOne;
            this.PlayerTwo = PlayerTwo;
        }

        public String getplayerOne() {
            return PlayerOne;
        }
        public String getplayerTwo() {
            return PlayerTwo;
        }

        public String getCurrentPlayer() {
            return CurrentPlayer;
        }

        public void changeCurrentPlayer(){
            if (CurrentPlayer.equals(PlayerOne)) {
                this.CurrentPlayer = PlayerTwo;
            }
            if (CurrentPlayer.equals(PlayerTwo)) {
                this.CurrentPlayer = PlayerOne;
            }
        }
    }

    //TODO
    //Identifies the turn player and changes the button accordingly
    public void selectGridSpace(int row, int col) {

        //We handle these checks here before passing them to GameFragment.java to obfuscate
        if(player_construct.getCurrentPlayer().equals(player_construct.getplayerOne())) {
            //TODO
            //Do something to the square in the GameFragment.java
        }
        if(player_construct.getCurrentPlayer().equals(player_construct.getplayerTwo())) {
            //TODO
            //Do something to the square in the GameFragment.java
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
    private boolean isWinner(){

        //Checking each row
        for (int row = 0; row < GRID_SIZE; row++) {
            int line_total = 0;
            for (int col = 0; col < GRID_SIZE; col++) {
                line_total = line_total +  mTicTacToeGrid[row][col];
                if (line_total == 3 || line_total == -3) {
                    winners_name = player_construct.getCurrentPlayer();
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
                    winners_name = player_construct.getCurrentPlayer();
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
            winners_name = player_construct.getCurrentPlayer();
            return true;
        }

        //Checking upward sloping diagonal
        int upwardSumAngle = 0;
        upwardSumAngle = mTicTacToeGrid[0][2] + mTicTacToeGrid[1][1] + mTicTacToeGrid[2][0];
        if (upwardSumAngle == -3 || upwardSumAngle == 3) {
            winners_name = player_construct.getCurrentPlayer();
            return true;
        }
        return false;
    }

    //Handles logic between isWinner() and isFull() to decide when the game is over
    //Called by the onClick button in GameFragment.java
    public boolean isGameOver() {
        //If the grid is full and there is not a winner: end the game
        if (isFull() && !(isWinner())){
            return true;
        }
        //If the there is a winner: end the game
        return isWinner();
    }
}
