package com.ns.mathwars;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity implements View.OnClickListener{
    private static final int REQUEST_CODE = 100;
    private static final int PLAYERS_COUNT = 2;

    private TextView playerView;
    private Button startButton;

    private int currentPlayerNumber = 1;
    private boolean[][] playersAnswers = new boolean[PLAYERS_COUNT][PLAYERS_COUNT];
//    private int[] points = new int[PLAYERS_COUNT];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerView = (TextView) findViewById(R.id.player);
        startButton = (Button) findViewById(R.id.start);
        startButton.setOnClickListener(this);
    }

    private void displayPlayer() {
        playerView.setText("Player " + currentPlayerNumber);
    }

    private void startGame() {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("playerNumber", currentPlayerNumber);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK) {
            boolean[] playerAnswers = data.getBooleanArrayExtra("playerAnswers");
            playersAnswers[currentPlayerNumber - 1] = playerAnswers;
        }

        if(currentPlayerNumber == PLAYERS_COUNT) {
            int[] points = MathWars.calculatePoints(playersAnswers[0], playersAnswers[1]);
            Intent resultsIntent = new Intent(this, ResultsActivity.class);
            resultsIntent.putExtra("points", points);
            startActivity(resultsIntent);
            finish();
        } else {
            currentPlayerNumber ++;
            displayPlayer();
        }
    }

    @Override
    public void onClick(View view) {
        startGame();
    }
}
