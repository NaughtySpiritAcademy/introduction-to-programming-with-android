package co.naughtyspirit.wackyracer;

/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 5/24/15.
 */
public class Car extends BaseVehicle {

    public Car(PaintColor color, Driver driver) {
        super(color, driver, 100, 320, 1);
    }

    @Override
    public VehicleType getVehicleType() {
        return VehicleType.CAR;
    }

    @Override
    public void moveLeft() {
        position -= 2;
    }

    @Override
    public void moveRight() {
        position += 2;
    }
}
