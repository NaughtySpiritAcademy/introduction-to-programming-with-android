package co.naughtyspirit.wackyracer;

/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 5/24/15.
 */
public class Car {
    private CarColor color;
    private Driver driver;
    private int topSpeed;
    private int position;

    public Car(CarColor color, Driver driver, int topSpeed, int position) {
        this.color = color;
        this.driver = driver;
        this.topSpeed = topSpeed;
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public CarColor getColor() {
        return color;
    }

    public Driver getDriver() {
        return driver;
    }

    public int getTopSpeed() {
        return topSpeed;
    }

    public void moveLeft() {
        position -= 1;
    }

    public void moveRight() {
        position += 1;
    }
}
