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

        int points1 = 0;
        for(int i = 0; i < player1Answers.length; i = i + 1) {
           boolean answer = player1Answers[i];
           if(answer) {
               points1 = points1 + 10;
           } else {
               points1 = points1 - 7;
           }
        }

        int points2 = 0;
        for(int i = 0; i < player2Answers.length; i = i + 1) {
            boolean answer = player2Answers[i];
            if(answer) {
                points2 = points2 + 10;
            } else {
                points2 = points2 - 7;
            }
        }

        return new int[] { points1, points2 };
    }

    public static int whoWins(int player1Points, int player2Points) {
        if(player1Points > player2Points) {
            return 1;
        } else {
            return 2;
        }
    }
}
