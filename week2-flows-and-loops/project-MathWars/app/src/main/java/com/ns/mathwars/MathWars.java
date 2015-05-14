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
        return new int[]{100, 200};
    }

    public static int whoWins(int player1Points, int player2Points) {
        return 1;
    }
}
