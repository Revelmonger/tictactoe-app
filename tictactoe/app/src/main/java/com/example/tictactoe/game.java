package com.example.tictactoe;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

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
        View view = inflater.inflate(R.layout.fragment_game, container, false);


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






        //TODO: create these objects
        //create player object in the game instance which we can use to refrence the current player and set the value of the buttons.
        //create a game instance that represents the board state. you can then call getCurrentPlayer() to find the current player

        // create player objects
       TicTacToeGame currentGameInstance = new TicTacToeGame();

        // create game instance
       TicTacToeGame.Players currentgameplayers = TicTacToeGame.Players(playerOneNameIs,playerTwoNameIs);


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
        public void onButtonClick(int row, int column) {
            if (Game.isSelected(row, column)) {
        } else
            Game.selectGridSpace(row, column);
            if (Game.Player.getCurrentPlayer() == Game.Player.PLAYER_ONE) {
                button00.setText(getString(R.string.x));
            }
            else {
                button00.setText(getString(R.string.o));
            }
            if (Game.isGameOver()) {}
            else {
                Game.players.changeCurrentPlayer();
            }



        //TODO: IMPLEMENT THE OTHER FUNCTIONS
       /* button00.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //This is working code for setting the buttons string value
                button00.setText(getString(R.string.x));
            }
        });*/






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
}

