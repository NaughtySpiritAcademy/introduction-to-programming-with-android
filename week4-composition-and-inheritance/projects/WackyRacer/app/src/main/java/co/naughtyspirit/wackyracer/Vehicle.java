package co.naughtyspirit.wackyracer;

/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 5/31/15.
 */
public interface Vehicle {

    int getPosition();

    PaintColor getColor();

    Driver getDriver();

    int getTopSpeed();

    void moveLeft();

    void moveRight();

    int getInitialSpeed();

    VehicleType getVehicleType();
}
