package co.naughtyspirit.wackyracer;

/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 5/27/15.
 */
public class Driver {

    private String name;
    private int points;
    private static int numOfDrivers = 0;

    public Driver(String name) {
        this.name = name;
        numOfDrivers++;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public static int getNumOfDrivers() {
        return numOfDrivers;
    }
}
