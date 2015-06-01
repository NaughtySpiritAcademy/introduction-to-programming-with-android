package co.naughtyspirit.wackyracer.entities;

import android.graphics.drawable.Drawable;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import co.naughtyspirit.wackyracer.Vehicle;
import co.naughtyspirit.wackyracer.utils.Constants;

/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 5/20/15.
 */
public class VehicleEntity extends GameEntity {

    private final Vehicle vehicle;
    private final VehicleCrashListener listener;


    public VehicleEntity(Drawable image, Vehicle vehicle, VehicleCrashListener listener) {
        super(new Position(Constants.BOARD_ROWS - 1, getStartPosition(vehicle.getPosition())), image);
        this.vehicle = vehicle;
        this.listener = listener;
    }

    private static int getStartPosition(int playerStartPosition) {
        if (playerStartPosition >= 1 && playerStartPosition <= 2) {
            return Constants.ROAD_COLUMNS[playerStartPosition - 1];
        }
        Random random = new Random();
        return Constants.ROAD_COLUMNS[random.nextInt(Constants.ROAD_COLUMNS.length)];
    }

    public void checkForCollisions(List<GameEntity> gameEntities) {
        for (GameEntity entity : gameEntities) {
            if (entity instanceof TrafficCarEntity) {
                if (position.equals(entity.position)) {
                    listener.onCarCrash();
                    return;
                }
            }
        }
        if (!Arrays.asList(Constants.ROAD_COLUMNS).contains(position.column)) {
            listener.onCarCrash();
        }
    }

    public int getTopSpeed() {
        return vehicle.getTopSpeed();
    }

    public int getInitialSpeed() {
        return vehicle.getInitialSpeed();
    }

    public void changePosition(int newColumn) {
        position = new Position(position.row, newColumn);
    }
}
