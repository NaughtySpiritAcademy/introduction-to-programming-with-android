package co.naughtyspirit.wackyracer;

/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 5/21/15.
 */
public class Car {

    private String color;
    private String driver;
    private int topSpeed;


    private int position;

    public Car(String color, String driver, int topSpeed, int position) {
        this.color = color;
        this.driver = driver;
        this.topSpeed = topSpeed;
        this.position = position;
    }

    public int getPosition() {
        return position;
    }


    public String getColor() {
        return color;
    }

    public String getDriver() {
        return driver;
    }

    public int getTopSpeed() {
        return topSpeed;
    }
}
