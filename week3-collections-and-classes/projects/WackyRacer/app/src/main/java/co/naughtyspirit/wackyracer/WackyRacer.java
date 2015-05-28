package co.naughtyspirit.wackyracer;

/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 5/21/15.
 */
public class WackyRacer {

    private Car car;

    public void createCar(String playerName) {
        car = new Car("blue", playerName, 400, 1);
    }

    public int moveLeft() {
        car.moveLeft();
        return car.getPosition();
    }

    public int moveRight() {
        car.moveRight();
        return car.getPosition();
    }

    public void setPoints(int points) {

    }

    public int getNumberOfPlayers() {
        return 0;
    }

    public Car getCar() {
        return car;
    }
}
