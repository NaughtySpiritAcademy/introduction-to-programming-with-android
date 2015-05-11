package co.naughtyspirit.spaceshipcommander;

/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 5/9/15.
 */
public class Commander {

    /**
     * Returns the correct type of ship for the specified passenger count
     *
     * If the number of passengers is below 10 - small ship
     * If the number of passengers is below 30 and above 9 - medium ship
     * Otherwise return big ship
     *
     * @param passengerCount the number of passenger the ship must carry
     * @return 1 - small ship, 2 - medium ship, 3 - big ship
     */
    public static int getShipType(int passengerCount) {
        return 1;
    }

    /**
     * Translates the commands to language that the ship navigator can understand
     *
     * Iterate over each command and create array with two numbers:
     * the number of moves across the x and y axis.
     * Add each element to a 2D array and return the translated commands
     *
     * @param commands List of commands where each command is one of the following: "Up", "Down", "Left" or "Right"
     * @return 2 dimensional array where each element contains array of 2 numbers
     */
    public static int[][] getCommands(String[] commands) {
        return new int[][]{{1, 1}};
    }
}
