package co.naughtyspirit.wackyracer;

/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 6/1/15.
 */
public class Motorcycle extends BaseVehicle {
    public Motorcycle(PaintColor color, Driver driver) {
        super(color, driver, 140, 340, 1);
    }

    @Override
    public VehicleType getVehicleType() {
        return VehicleType.MOTORCYCLE;
    }

    @Override
    public void moveLeft() {
        position -= 1;
    }

    @Override
    public void moveRight() {
        position += 1;
    }
}
