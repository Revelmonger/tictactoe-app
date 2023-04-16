//GameFragment is responsible for displaying the game grid and manipulating the Model using the LightsOutGame class

package com.app.tictactoe;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

public class GameFragment extends Fragment {
    private final String GAME_STATE = "gameState";
    private TicTacToeGame mGame;
    private GridLayout mTicTacToeGrid;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View parentView = inflater.inflate(R.layout.fragment_game, container, false);

        // Add the same click handler to all grid buttons
        mTicTacToeGrid = parentView.findViewById(R.id.light_grid);
        for (int i = 0; i < mTicTacToeGrid.getChildCount(); i++) {
            Button gridButton = (Button) mTicTacToeGrid.getChildAt(i);
            gridButton.setOnClickListener(this::onLightButtonClick);
        }

        Button newGameBtn = parentView.findViewById(R.id.new_game_button);
        newGameBtn.setOnClickListener(v -> startGame());

        // Load preferred "on" button color
        SharedPreferences sharedPref = this.requireActivity().getPreferences(Context.MODE_PRIVATE);
        int onColorId = sharedPref.getInt("color", R.color.yellow);



        mGame = new TicTacToeGame();

        if (savedInstanceState == null) {
            startGame();
        }
        else {
            String gameState = savedInstanceState.getString(GAME_STATE);
            mGame.setState(gameState);
            setButtonColors();
        }

        return parentView;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(GAME_STATE, mGame.getState());
    }

    //Start Game Function
    private void startGame() {
        mGame.newGame();
        setButtonColors();
    }

    //This logic can be repurposed to handel the onclick
    // events for the button depending on the active user
    private void onLightButtonClick(View view) {

        // Find the button's row and col
        int buttonIndex = mTicTacToeGrid.indexOfChild(view);
        int row = buttonIndex / TicTacToeGame.GRID_SIZE;
        int col = buttonIndex % TicTacToeGame.GRID_SIZE;


        mGame.selectGridSpace(row, col);
        setButtonColors();

        // Congratulate the user if the game is over
        if (mGame.isGameOver()) {
            Toast.makeText(this.requireActivity(), R.string.congrats, Toast.LENGTH_SHORT).show();
        }
    }


}
