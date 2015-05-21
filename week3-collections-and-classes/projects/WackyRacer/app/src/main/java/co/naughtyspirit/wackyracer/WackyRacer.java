package co.naughtyspirit.wackyracer;

/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 5/21/15.
 */
public class WackyRacer {

    private Car car;

    public void createCar() {
        car = new Car("blue", "Polisan", 300, 2);
    }

    public int moveLeft() {
        car.moveLeft();
        return car.getPosition();
    }

    public int moveRight() {
        car.moveRight();
        return car.getPosition();
    }

    public Car getCar() {
        return car;
    }
}
