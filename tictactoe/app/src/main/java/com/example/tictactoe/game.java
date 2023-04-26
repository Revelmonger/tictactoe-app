package com.example.tictactoe;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;


public class game extends Fragment {

    // TODO: Create a pop up Winner Congratz thing.
    // I have a tutorial you can follow if you want to do this one

    View view;

    String playerOneNameIs, playerTwoNameIs;

    TextView Player_1_Name, Player_2_Name, TurnPlayerText, CurrentPlayerSymbol;

    Button button00, button01, button02;
    Button button10, button11, button12;
    Button button20, button21, button22;

    Button new_game_button, exit_button;

    private static final String ARG_PARAM1 = "Player1Name";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;


    // create player objects
    TicTacToeGame currentGameInstance;


    TicTacToeGame.Players currentGamePlayers;




    public game() {
        // Required empty public constructor
    }


    public static game newInstance(String param1, String param2) {
        game fragment = new game();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_game, container, false);


        Player_1_Name = (TextView) view.findViewById(R.id.Player_1_Name);
        Player_2_Name = (TextView) view.findViewById(R.id.Player_2_Name);

        button00 = (Button) view.findViewById(R.id.button00);
        button01 = (Button) view.findViewById(R.id.button01);
        button02 = (Button) view.findViewById(R.id.button02);
        button10 = (Button) view.findViewById(R.id.button10);
        button11 = (Button) view.findViewById(R.id.button11);
        button12 = (Button) view.findViewById(R.id.button12);
        button20 = (Button) view.findViewById(R.id.button20);
        button21 = (Button) view.findViewById(R.id.button21);
        button22 = (Button) view.findViewById(R.id.button22);


        currentGameInstance =  new TicTacToeGame();
        currentGamePlayers = new TicTacToeGame.Players(playerOneNameIs,playerTwoNameIs);



    /*
       button00.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO: Add function call here pass in row,col
                //button00.setText(getString(R.string.x));

                    }
                });

            }
        });
        */


        button00.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int row = 0;
                int col = 0;
                // call your function and pass in row and col values
                RowsColl(row, col);
                // set the text of the button to "X"
                button00.setText(getString(R.string.x));
            }
        });


        Bundle bundle=getArguments();
        if(bundle != null){
            //Extracts the strings from the bundle
            playerOneNameIs = bundle.getString("playerOne", null);
            playerTwoNameIs = bundle.getString("playerTwo", null);
            //Sets the text of the players names to the corresponding TextViews
            Player_1_Name.setText(playerOneNameIs);
            Player_2_Name.setText(playerTwoNameIs);
        }




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




    //TODO: MAKE A FUNCTION LIKE THIS
    //If isSelected(row, column) = true do nothing
    //else
    // call selectGridSpace(row, column)
    // button00.setText(Game.Player.getCurrentPlayer()); //Use getString(R.string.x) or return o in the function return
    // if  Game.isGameOver() to check if winner
    //Dialog
    // else
    // Game.players.changeCurrentPlayer()
    public void onButtonClick(int row, int col) {

            if (currentGameInstance.isSelected(row, col)) {
            } else
                currentGameInstance.selectGridSpace(row, col);
            if (currentGameInstance.Player.getCurrentPlayer() == currentGameInstance.Player.PLAYER_ONE) {
                button00.setText(getString(R.string.x));
            } else {
                button00.setText(getString(R.string.o));
            }
            if (currentGameInstance.isGameOver()) {
               //TODO Buzzed: Pop up dialog box

            } else {
                currentGameInstance.players.changeCurrentPlayer();
            }


    }

    public void RowsColl(int row, int col) {
        Log.d("TAG", "Button clicked at row " + row + ", column " + col);
    }

    public void createPopUp() {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(view.getContext());

        // Set the message show for the Alert time
        builder.setMessage("Do you want to exit ?");

        // Set Alert Title
        builder.setTitle("Alert !");

        // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
        builder.setCancelable(false);

        // Set the positive button with yes name Lambda OnClickListener method is use of DialogInterface interface.
        builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
            // When the user click yes button then app will close
            finish();
        });

        // Set the Negative button with No name Lambda OnClickListener method is use of DialogInterface interface.
        builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
            // If user click no then dialog box is canceled.
            dialog.cancel();
        });

        // Create the Alert dialog
        AlertDialog alertDialog = builder.create();
        // Show the Alert Dialog box
        alertDialog.show();

    }


}






