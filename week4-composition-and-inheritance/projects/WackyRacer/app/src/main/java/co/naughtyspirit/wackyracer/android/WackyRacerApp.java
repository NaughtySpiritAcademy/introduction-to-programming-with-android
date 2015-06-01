package co.naughtyspirit.wackyracer.android;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import co.naughtyspirit.wackyracer.Car;
import co.naughtyspirit.wackyracer.Vehicle;

/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 5/27/15.
 */
public class WackyRacerApp extends Application {

    private List<Vehicle> vehicles = new ArrayList<>();

    public void addCar(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }
}
