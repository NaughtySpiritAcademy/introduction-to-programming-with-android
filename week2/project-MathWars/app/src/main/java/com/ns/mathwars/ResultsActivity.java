package com.ns.mathwars;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class ResultsActivity extends Activity implements View.OnClickListener{

    private TextView winnerView;
    private TextView player1ResultView;
    private TextView player2ResultView;
    private Button playAgainButton;

    private int[] results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        results = getIntent().getIntArrayExtra("results");

        winnerView = (TextView) findViewById(R.id.winner);
        player1ResultView = (TextView) findViewById(R.id.player1_result);
        player2ResultView = (TextView) findViewById(R.id.player2_result);
        playAgainButton = (Button) findViewById(R.id.play_again);
        playAgainButton.setOnClickListener(this);

        displayWinner();
        displayPlayer1Result();
        displayPlayer2Result();
    }

    private void displayWinner() {
        int winnerNumber = MathWars.whoWins(results[0], results[1]);
        winnerView.setText("Player " + winnerNumber + " wins!");
        if(winnerNumber == 1) {
            player1ResultView.setBackgroundColor(getResources().getColor(R.color.green));
            player2ResultView.setBackgroundColor(getResources().getColor(R.color.red));
        } else {
            player1ResultView.setBackgroundColor(getResources().getColor(R.color.red));
            player2ResultView.setBackgroundColor(getResources().getColor(R.color.green));
        }
    }

    private void displayPlayer1Result() {
        player1ResultView.setText("Player 1: " + results[0]);
    }

    private void displayPlayer2Result() {
        player2ResultView.setText("Player 2: " + results[1]);
    }


    @Override
    public void onClick(View view) {
        Intent startGameIntent = new Intent(this, MainActivity.class);
        startActivity(startGameIntent);
        finish();
    }
}
