package com.ns.mathwars;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;


public class GameActivity extends Activity implements View.OnClickListener {
    private static final int MAX_NUMBER = 50;
    private static final int TIME_MILLISECONDS = 10000;
    private static final int CORRECT_ANSWER_POINTS = 10;
    private static final int WRONG_ANSWER_POINTS = - 7;

    private TextView playerView;
    private TextView equationView;
    private ImageButton rightButton;
    private ImageButton wrongButton;
    private TextView attempsView;
    private TextView timeView;

    private Operator[] operators = Operator.values();
    private Equation currentEquation;
    private int allAttempts = 0;
    private int correctAttempts = 0;
    private CountDownTimer timer;
    private int playerNumber = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        playerNumber = getIntent().getIntExtra("playerNumber", playerNumber);
        playerView = (TextView) findViewById(R.id.player);
        equationView = (TextView) findViewById(R.id.equation);
        rightButton = (ImageButton) findViewById(R.id.right);
        wrongButton = (ImageButton) findViewById(R.id.wrong);
        rightButton.setOnClickListener(this);
        wrongButton.setOnClickListener(this);
        attempsView = (TextView) findViewById(R.id.attemps);
        timeView = (TextView) findViewById(R.id.time);


        timer = new CountDownTimer(TIME_MILLISECONDS, 1000) {
            @Override
            public void onTick(long l) {
                displayTime(l);
            }

            @Override
            public void onFinish() {
                displayTime(0);
                finishGame();
            }

        };

        displayPlayer();
        displayAttempts();
        displayTime(TIME_MILLISECONDS);
        changeEquation();
        timer.start();
    }

    private void changeEquation() {
        currentEquation = createEquation();
        displayEquation();
    }

    @Override
    public void onClick(View view) {
        boolean answer = false;
        switch (view.getId()) {
            case R.id.right:
                answer = true;
                break;
            case R.id.wrong:
                answer = false;
                break;
        }

        if(MathWars.isCorrect(currentEquation.firstNumber, currentEquation.secondNumber,
                currentEquation.operator.getSign(), answer)) {
            correctAttempts ++;
        }
        allAttempts ++;

        displayAttempts();
        changeEquation();
    }

    private void displayPlayer() {
        playerView.setText("Player " + playerNumber);
    }


    private void displayAttempts() {
        attempsView.setText(correctAttempts + "/" + allAttempts);
    }

    private void displayTime(long milliseconds) {
        timeView.setText(milliseconds / 1000 + "");
    }

    private void displayEquation() {
        equationView.setText(currentEquation.convertToString());
    }


    private Equation createEquation() {
        Equation equation = new Equation();
        Operator operator = getRandomOperator();
        equation.operator = operator;

        switch (operator) {
            case ADDITION:
            case SUBTRACTION:
                equation.firstNumber = getRandomNumber();
                equation.secondNumber = getRandomNumber();
                break;

            case MULTIPLICATION:
                equation.firstNumber = getRandomNumber();
                equation.secondNumber = getRandom1DigitNumber();
                break;
            case DIVISION:
                while (equation.secondNumber == 0) {
                    equation.secondNumber = getRandom1DigitNumber();
                }
                int divisionResult = getRandomNumber();
                equation.firstNumber = equation.secondNumber * divisionResult;
                break;
        }

        equation.generateResult();

        return equation;
    }

    private Operator getRandomOperator() {
        Random random = new Random();
        int index = random.nextInt(operators.length);
        return operators[index];
    }

    private int getRandomNumber() {
        Random digitsNumberRandom = new Random();
        int digitsNumber = digitsNumberRandom.nextInt(2) + 1;
        if(digitsNumber == 1) {
            return getRandom1DigitNumber();
        }
        return getRandom2DigitsNumber();
    }

    private int getRandom2DigitsNumber() {
        Random random = new Random();
        return random.nextInt(MAX_NUMBER - 10) + 10;
    }

    private int getRandom1DigitNumber() {
        Random random = new Random();
        return random.nextInt(10);
    }

    private void finishGame() {
        final int points = computePoints();
        AlertDialog.Builder builder = new AlertDialog.Builder(this, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
        builder.setTitle("Game Over");
        builder.setMessage("Total points: " + points);
        builder.setCancelable(false);
        builder.setNeutralButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        Intent data = new Intent();
                        data.putExtra("points", points);
                        setResult(Activity.RESULT_OK, data);
                        finish();
                    }
                });

        AlertDialog gameOverDialog = builder.create();
        gameOverDialog.show();
    }

    private int computePoints() {
        int wrongAnswers = allAttempts - correctAttempts;
        return correctAttempts * CORRECT_ANSWER_POINTS + wrongAnswers * WRONG_ANSWER_POINTS;
    }

    class Equation {
        int firstNumber = 0;
        int secondNumber = 0;
        Operator operator;
        int result;

        String convertToString() {
            String str = firstNumber + " ";
            str += operator.getSign() + " ";
            str += secondNumber + " ";
            str += "= " + result;
            return str;
        }

        void generateResult() {
            boolean isCorrect = new Random().nextInt(2) == 0;
            if(isCorrect) {
                result = getRealAnswer();
            } else {
                int[] mistakeRange = new int[6];
                int number = -3;
                for(int i = 0; i < 6; i++) {
                    mistakeRange[i] = number;
                    number ++;
                }

                result = getRealAnswer() + mistakeRange[new Random().nextInt(mistakeRange.length)];
            }
        }

        int getRealAnswer() {
            int answer = 1;
            switch (operator) {
                case ADDITION:
                    answer = firstNumber + secondNumber;
                    break;
                case SUBTRACTION:
                    answer = firstNumber - secondNumber;
                    break;
                case MULTIPLICATION:
                    answer = firstNumber * secondNumber;
                    break;
                case DIVISION:
                    answer = firstNumber / secondNumber;
                    break;
            }
            return answer;
        }

    }

    enum Operator {
        ADDITION('+'), SUBTRACTION('-'), MULTIPLICATION('*'), DIVISION('/');

        char sign;

        Operator(char sign) {
            this.sign = sign;
        }

        char getSign() {
            return sign;
        }

    }

    @Override
    public void onBackPressed() {
    }

}
