package com.ns.mathwars;

import java.util.Random;

/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 5/9/15.
 */
public class MathWars {

    public static boolean isCorrect(int firstNumber, int secondNumber, char operator, boolean playerAnswer) {
        return new Random().nextBoolean();
    }

    public static int whoWins(int player1Result, int player2Result) {
        return new Random().nextInt(2) + 1;
    }
}
