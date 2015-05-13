package com.ns.mathwars;

/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 5/9/15.
 */
public class MathWars {

    public static boolean isCorrect(int firstNumber, int secondNumber, int result, char operator, boolean playerAnswer) {
        int realResult = 0;

        switch (operator) {
            case '+': realResult = firstNumber + secondNumber;
                break;
            case '-': realResult = firstNumber - secondNumber;
                break;
            case '*': realResult = firstNumber * secondNumber;
                break;
            case '/': realResult = firstNumber / secondNumber;
        }

        boolean isEquationCorrect;
        if(realResult == result) {
            isEquationCorrect = true;
        } else {
            isEquationCorrect = false;
        }

        boolean isPlayerAnsweredRight;
        if(isEquationCorrect && playerAnswer) {
            isPlayerAnsweredRight = true;
        } else if(!isEquationCorrect && !playerAnswer){
            isPlayerAnsweredRight = true;
        } else{
            isPlayerAnsweredRight = false;
        }

        return isPlayerAnsweredRight;
    }

    public static int[] calculatePoints(boolean[] player1Answers, boolean[] player2Answers) {
        int player1Points = 0;
        for(int i=0; i<player1Answers.length; i++) {
            boolean answer = player1Answers[i];
            if(answer) {
                player1Points = player1Points + 10;
            } else {
                player1Points = player1Points - 7;
            }
        }

        int player2Points = 0;
        for(int i=0; i<player2Answers.length; i++) {
            boolean answer = player2Answers[i];
            if(answer) {
                player2Points = player2Points + 10;
            } else {
                player2Points = player2Points - 7;
            }
        }

//        player1Points = calculatePlayerPoints(player1Answers);
//        player2Points = calculatePlayerPoints(player2Answers);

        int[] points = new int[]{player1Points, player2Points};
        return points;
    }

    private static int calculatePlayerPoints(boolean[] playerAnswers) {
        int points = 0;
        for(int i=0; i<playerAnswers.length; i++) {
            boolean answer = playerAnswers[i];
            if(answer) {
                points = points + 10;
            } else {
                points = points - 7;
            }
        }
        return points;
    }

    public static int whoWins(int player1Points, int player2Points) {
        int winner;
        if(player1Points > player2Points) {
            winner = 1;
        } else {
            winner = 2;
        }
        return winner;
    }
}
