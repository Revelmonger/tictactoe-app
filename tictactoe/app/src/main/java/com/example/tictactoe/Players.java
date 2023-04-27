package com.example.tictactoe;

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


    public String getCurrentPlayerIcon() {
        if (CurrentPlayer.equals(PlayerOne)) {
            return "R.strings.x";
        } else {
            return "R.strings.o";
        }
    }
    public String getCurrentPlayer() {
        if (CurrentPlayer.equals(PlayerOne)) {
            return PlayerOne;
        } else {
            return PlayerTwo;
        }
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