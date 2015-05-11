package co.naughtyspirit.spaceshipcommander;

/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 5/9/15.
 */
public class Commander {

    public static int[][] getCommands(String[] commandTexts) {
        int[][] commands = new int[commandTexts.length][];
        for (int i = 0; i < commandTexts.length; i++) {
            String commandText = commandTexts[i];
            switch (commandText) {
                case "Up":
                    commands[i] = new int[]{0, -1};
                    break;
                case "Down":
                    commands[i] = new int[]{0, 1};
                    break;
                case "Left":
                    commands[i] = new int[]{-1, 0};
                    break;
                case "Right":
                    commands[i] = new int[]{1, 0};
                    break;
            }
        }
        return commands;
//        return new int[][]{{1, 1}};
    }

    public static int getShip(int passengerCount) {
        if (passengerCount < 10) {
            return 1;
        } else if (passengerCount < 30) {
            return 2;
        }
        return 3;
//        return 1;
    }
}
