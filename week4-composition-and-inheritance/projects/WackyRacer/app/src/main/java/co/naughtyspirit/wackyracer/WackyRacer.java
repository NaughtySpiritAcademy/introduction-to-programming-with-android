package co.naughtyspirit.wackyracer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 5/21/15.
 */
public class WackyRacer {

    private Vehicle vehicle;

    public void createVehicle(VehicleType vehicleType, String playerName) {
        Driver driver = new Driver(playerName);
        switch (vehicleType) {
            case CAR:
                vehicle = new Car(PaintColor.RED, driver);
                break;
            case MOTORCYCLE:
                vehicle = new Motorcycle(PaintColor.BLUE, driver);
                break;
            default:
                vehicle = new Spaceship(PaintColor.BLUE, driver);
        }
    }

    public int moveLeft() {
        vehicle.moveLeft();
        return vehicle.getPosition();
    }

    public int moveRight() {
        vehicle.moveRight();
        return vehicle.getPosition();
    }

    public void setPoints(int points) {
        vehicle.getDriver().setPoints(points);
    }

    public int getNumberOfPlayers() {
        return Driver.getNumOfDrivers();
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}
