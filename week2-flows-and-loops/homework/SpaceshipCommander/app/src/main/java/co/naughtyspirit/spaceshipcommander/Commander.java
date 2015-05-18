package co.naughtyspirit.spaceshipcommander;

import java.util.ArrayDeque;

/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 5/9/15.
 */
public class Commander {

    /**
     * Returns the correct type of ship for the specified passenger count
     * <p/>
     * If the number of passengers is below 10 - small ship
     * If the number of passengers is below 30 and above 9 - medium ship
     * Otherwise return big ship
     *
     * @param passengerCount the number of passenger the ship must carry
     * @return 1 - small ship, 2 - medium ship, 3 - big ship
     */
    public static int getShipType(int passengerCount) {
        if (passengerCount < 10) {
            return 1;
        } else if (passengerCount < 30) {
            return 2;
        }
        return 3;
    }

    /**
     * Translates the commands to language that the ship navigator can understand
     * <p/>
     * Iterate over each command and create array with two numbers:
     * the number of moves across the x and y axis.
     * Add each element to a 2D array and return the translated commands
     *
     * @param commands List of commands where each command is one of the following: "Up", "Down", "Left" or "Right"
     * @return 2 dimensional array where each element contains array of 2 numbers
     */
    public static int[][] getCommands(String[] commands) {
        int[][] result = new int[commands.length][];
        for (int i = 0; i < commands.length; i++) {
            String commandText = commands[i];
            switch (commandText) {
                case "Up":
                    result[i] = new int[]{0, -1};
                    break;
                case "Down":
                    result[i] = new int[]{0, 1};
                    break;
                case "Left":
                    result[i] = new int[]{-1, 0};
                    break;
                case "Right":
                    result[i] = new int[]{1, 0};
                    break;
            }
        }
        return result;
    }

    public static ArrayDeque<int[]> translateCommands(String[] textCommands) {
        ArrayDeque<int[]> commandQueue = new ArrayDeque<>();
        int[][] commands = Commander.getCommands(textCommands);
        for (int[] command : commands) {
            commandQueue.add(new int[]{command[0], command[1]});
        }
        return commandQueue;
    }
}
