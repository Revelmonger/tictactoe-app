package com.example.tictactoe;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class game extends Fragment {
    public game() {
        // Required empty public constructor
    }
    public static game newInstance(String param1, String param2) {
        game fragment = new game();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    View view;

    String playerOneNameIs, playerTwoNameIs;

    String currentPlayerIs;

    //XML Imports
    TextView Player_1_NameTextView, Player_2_NameTextView;

    Button button00, button01, button02;
    Button button10, button11, button12;
    Button button20, button21, button22;

    Button new_game_button, exit_button;

    // create  objects
  //  TicTacToeGame currentGameInstance;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_game, container, false);


        Player_1_NameTextView = (TextView) view.findViewById(R.id.Player_1_Name);
        Player_2_NameTextView = (TextView) view.findViewById(R.id.Player_2_Name);


        button00 = (Button) view.findViewById(R.id.button00);
        button01 = (Button) view.findViewById(R.id.button01);
        button02 = (Button) view.findViewById(R.id.button02);
        button10 = (Button) view.findViewById(R.id.button10);
        button11 = (Button) view.findViewById(R.id.button11);
        button12 = (Button) view.findViewById(R.id.button12);
        button20 = (Button) view.findViewById(R.id.button20);
        button21 = (Button) view.findViewById(R.id.button21);
        button22 = (Button) view.findViewById(R.id.button22);

        Bundle bundle=getArguments();
        if(bundle != null){
            //Extracts the strings from the bundle
            playerOneNameIs = bundle.getString("playerOne", null);
            playerTwoNameIs = bundle.getString("playerTwo", null);
            //Sets current player when bundle is pulled
            currentPlayerIs = playerOneNameIs;
            //Sets the text of the players names to the corresponding TextViews
            Player_1_NameTextView.setText(playerOneNameIs);
            Player_2_NameTextView.setText(playerTwoNameIs);
        }

        //currentGameInstance =  new TicTacToeGame();

        button00.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int row = 0;
                int col = 0;
                onButtonClick(row, col,button00);
            }
        });

        button01.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int row = 0;
                int col = 1;
                onButtonClick(row, col,button01);
            }
        });

        //onClick event for the Exit Button
        exit_button = view.findViewById(R.id.exit_button);
        exit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new home();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                transaction.replace(R.id.nav_host_fragment, fragment ).commit();

            }
        });

        //onClick event for the New Game Button
        new_game_button = view.findViewById(R.id.new_game_button);
        new_game_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game newGameInstance = new game();
                
                //Assigns the bundle as an argument for the game
                newGameInstance.setArguments(bundle);

                //Creates a new fragment from the game
                Fragment fragment = newGameInstance;
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, fragment ).commit();
            }
        });

        return view;
    }


    public void changeCurrentPlayer(){

        if (currentPlayerIs == playerOneNameIs) {
            currentPlayerIs = playerTwoNameIs;
        } else if (currentPlayerIs == playerTwoNameIs) {
            currentPlayerIs = playerOneNameIs;
        } else {
            currentPlayerIs = playerTwoNameIs;
        }
    }
    public void onButtonClick(int row, int col,Button currentButton) {
        currentButton.setText(getCurrentPlayerIcon());
        changeCurrentPlayer();

/*
        if (currentGameInstance.isSelected(row, col)) {
            // do nothing, the button has already been selected
        } else {
            // select the grid space
            currentGameInstance.selectGridSpace(row, col);

            currentButton.setText(getCurrentPlayerIcon());
            changeCurrentPlayer();
            /*
            if (currentGameInstance.isGameOver()) {
                //TODO put pop up message
                if (currentGameInstance.isWinner()) {
                    // createPopUp(1);
                } else {
                    //   createPopUp(2);
                }
            } else {



            }
*/
    }





    public String getCurrentPlayerIcon(){
        if (currentPlayerIs.equals(playerOneNameIs)) {
            return "x";
        } else if (currentPlayerIs.equals(playerTwoNameIs)) {
            return "o";
        } else {
            return "Error";
        }

    }



        public void createPopUp(int gameOverState){
            if (gameOverState == 1){
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(view.getContext());

                // Set the message show for the Alert time
                alertBuilder.setMessage(" is the winner!");

                // Set Alert Title
                alertBuilder.setTitle("Congratulations!");

                // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
                alertBuilder.setCancelable(true);

                // Create the Alert dialog
                AlertDialog alertDialog = alertBuilder.create();
                // Show the Alert Dialog box
                alertDialog.show();
            }
            else if (gameOverState == 2){
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(view.getContext());

                // Set Alert Title
                alertBuilder.setTitle("It's a Tie!");

                // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
                alertBuilder.setCancelable(true);

                // Create the Alert dialog
                AlertDialog alertDialog = alertBuilder.create();
                // Show the Alert Dialog box
                alertDialog.show();
            }

        }



/*

    public class TicTacToeGame {


        public static final int GRID_SIZE = 3;

        // Creates an array of integer values
        private Integer[][] mTicTacToeGrid;

        //Resizes the array using the GRID_SIZE
        public TicTacToeGame() {


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
            if (mTicTacToeGrid[row][col] != 0){
                return true;
            }
            return false;
        }




        //Identifies the turn player and changes the button accordingly
        public void selectGridSpace(int row, int col) {

            //We handle these checks here before passing them to GameFragment.java to obfuscate
            if(currentGamePlayers.getCurrentPlayer().equals(currentGamePlayers.getplayerOne())) {
                mTicTacToeGrid[row][col] = 1;
            }
            if(currentGamePlayers.getCurrentPlayer().equals(currentGamePlayers.getplayerTwo())) {
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
        public boolean isWinner(){

            //Checking each row
            for (int row = 0; row < GRID_SIZE; row++) {
                int line_total = 0;
                for (int col = 0; col < GRID_SIZE; col++) {
                    line_total = line_total +  mTicTacToeGrid[row][col];
                    if (line_total == 3 || line_total == -3) {

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

                return true;
            }

            //Checking upward sloping diagonal
            int upwardSumAngle = 0;
            upwardSumAngle = mTicTacToeGrid[0][2] + mTicTacToeGrid[1][1] + mTicTacToeGrid[2][0];
            if (upwardSumAngle == -3 || upwardSumAngle == 3) {

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

*/





}






