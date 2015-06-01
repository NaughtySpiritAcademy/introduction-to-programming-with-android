package co.naughtyspirit.wackyracer;

/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 5/31/15.
 */
public abstract class BaseVehicle implements Vehicle {

    private PaintColor color;
    private Driver driver;
    private final int initialSpeed;
    private int topSpeed;
    protected int position;

    public BaseVehicle(PaintColor color, Driver driver, int initialSpeed, int topSpeed, int position) {
        this.color = color;
        this.driver = driver;
        this.initialSpeed = initialSpeed;
        this.topSpeed = topSpeed;
        this.position = position;
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public PaintColor getColor() {
        return color;
    }

    @Override
    public Driver getDriver() {
        return driver;
    }

    @Override
    public int getTopSpeed() {
        return topSpeed;
    }

    @Override
    public int getInitialSpeed() {
        return initialSpeed;
    }

}
