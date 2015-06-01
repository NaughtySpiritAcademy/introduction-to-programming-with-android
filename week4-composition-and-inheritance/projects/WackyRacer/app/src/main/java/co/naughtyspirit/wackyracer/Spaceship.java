package co.naughtyspirit.wackyracer;

/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 6/1/15.
 */
public class Spaceship extends BaseVehicle {
    public Spaceship(PaintColor color, Driver driver) {
        super(color, driver, 240, 400, 1);
    }

    @Override
    public VehicleType getVehicleType() {
        return VehicleType.SPACESHIP;
    }

    @Override
    public void moveLeft() {
        position -= 3;
    }

    @Override
    public void moveRight() {
        position += 3;
    }
}
