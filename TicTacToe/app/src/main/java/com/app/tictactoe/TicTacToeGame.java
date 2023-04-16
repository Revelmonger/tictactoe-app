package com.app.tictactoe;
import java.util.Random;

public class TicTacToeGame {
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
        if (mTicTacToeGrid[row][col] == 0){
            return false;
        }
        return true;
    }

    //TODO - I think I did the constructor correctly. IDK someone review it.
    //Handel and call the player class from this class by calling it when the onStart button is clicked in the HomeFragment
    public class Players {

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

        public String changeCurrentPlayer(){
            if (CurrentPlayer == PlayerOne) {
                this.CurrentPlayer = PlayerTwo;
            }
            if (CurrentPlayer == PlayerTwo) {
                this.CurrentPlayer = PlayerOne;
            }
        }
    }

    //TODO
    //Identifies the turn player and changes the button accordingly 
    public void selectGridSpace(int row, int col) {

        //We handle these checks here before passing them to GameFragment.java to obfuscate
        if(Players.getCurrentPlayer == Players.getplayerOne) {
            //TODO
            //Do something to the square in the GameFragment.java 
        }
        if(Players.getCurrentPlayer == Players.getplayerTwo) {
            //TODO
            //Do something to the square in the GameFragment.java 
        }
    }


    //Checks if the array is full leading to a tie
    private boolean isFull() {
        int row, column;
        boolean status = true;
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                if (mTicTacToeGrid[row][column] == 0) {
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
            Integer line_total = 0;
            for (int col = 0; col < GRID_SIZE; col++) {
                line_total = line_total +  mTicTacToeGrid[row][col];
                if (line_total == 3 || line_total == -3) {
                    //TODO
                    //we can add logic here to return the winners name
                    //but it may be a better idea to use this function
                    //in a if statement then if use the current_player
                    //variable to declare winner and show winner's popup
                    return true;
                }
            }
        }

        //Checking each column
        for (int col = 0; col < GRID_SIZE; col++) {
            Integer line_total = 0;
            for (int row = 0; row < GRID_SIZE; row++) {
                line_total = line_total +  mTicTacToeGrid[row][col];
                if (line_total == 3 || line_total == -3) {
                    Players.getCurrentPlayer
                    //TODO
                    //we can add logic here to return the winners name
                    //but it may be a better idea to use this function
                    //in a if statement then if use the current_player
                    //variable to declare winner and show winner's popup
                    return true;
                }
            }
        }

        //TODO
        //Add in the code for the diagonals. It should already be implemented in the fxml version
        return false;
    }

    //Handels logic between isWinner() and isFull() to decide when the game is over
    //Called by the onClick button in GameFragment.java
    public boolean isGameOver() {
        //If the grid is full and there is not a winner: end the game
        if (isFull() && !(isWinner())){
            return true;
        }
        //If the there is a winner: end the game
        if (isWinner()){
            return true;
        }
        return false;
    }


}






/*
    public String getState() {
        StringBuilder boardString = new StringBuilder();
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                char value = mLightsGrid[row][col] ? 'T' : 'F';
                boardString.append(value);
            }
        }

        return boardString.toString();
    }

    public void setState(String gameState) {
        int index = 0;
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                mLightsGrid[row][col] = gameState.charAt(index) == 'T';
                index++;
            }
        }
    }
*/