package com.example.tictactoe;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class home extends Fragment {

    Button  startGameButton;
    EditText playerOneEditText, playerTwoEditText;

    String playerOne, playerTwo;

    public home() {
        // Required empty public constructor
    }

    //We don't really use this with our home page
    public static home newInstance(String param1, String param2) {
        home fragment = new home();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    //We don't really use this with our fragment based app
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Creates a view object representing the current view fragment_home
        View view =  inflater.inflate(R.layout.fragment_home, container, false);

        //Creating objects must be done during onCreateView and not inside a listener.
        playerOneEditText = (EditText) view.findViewById(R.id.playerOneEditText);
        playerTwoEditText = (EditText) view.findViewById(R.id.playerTwoEditText);
        startGameButton = (Button) view.findViewById(R.id.start_button);

        //Create a bundle to pass parameters
        Bundle transferBundle = new Bundle();


        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Gets the text from the textedit
                playerOne = playerOneEditText.getText().toString();
                playerTwo = playerTwoEditText.getText().toString();

                //Assigns the text to key:value pairs in the bundle
                transferBundle.putString("playerOne", playerOne);
                transferBundle.putString("playerTwo", playerTwo);

                //Creates a game refrence object
                game playerNames = new game();

                //Assigns the bundle as an argument for the game
                playerNames.setArguments(transferBundle);

                //Creates a new fragment from the game
                Fragment fragment = playerNames;

                //Switches the fragment in the nav_host_fragment
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, fragment ).commit();


            }
        });

        return view;
    }




}