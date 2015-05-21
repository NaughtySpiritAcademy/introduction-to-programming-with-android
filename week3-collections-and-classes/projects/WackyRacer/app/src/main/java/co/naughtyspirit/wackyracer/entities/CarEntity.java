package co.naughtyspirit.wackyracer.entities;

import android.graphics.drawable.Drawable;

import java.util.Arrays;
import java.util.List;

import co.naughtyspirit.wackyracer.Constants;

/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 5/20/15.
 */
public class CarEntity extends GameEntity {

    private final int topSpeed;
    private final CarCrashListener listener;

    public CarEntity(Drawable image, int startColumn, int topSpeed, CarCrashListener listener) {
        super(new Position(Constants.BOARD_ROWS - 1, startColumn), image);
        this.topSpeed = topSpeed;
        this.listener = listener;
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
        return topSpeed;
    }

    public void changePosition(int newColumn) {
        position = new Position(position.row, newColumn);
    }
}
