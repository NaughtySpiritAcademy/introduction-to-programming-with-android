package co.naughtyspirit.wackyracer;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 5/27/15.
 */
public class WackyRacerApp extends Application {

    private List<Car> cars = new ArrayList<>();

    public void addCar(Car car) {
        cars.add(car);
    }

    public List<Car> getCars() {
        return cars;
    }
}
