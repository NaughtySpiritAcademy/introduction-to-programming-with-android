package co.naughtyspirit.wackyracer.entities;

import android.graphics.drawable.Drawable;

import java.util.List;

import co.naughtyspirit.wackyracer.Constants;

/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 5/20/15.
 */
public class CarEntity extends GameEntity {

    private final CarCrashListener listener;

    public CarEntity(Drawable image, CarCrashListener listener) {
        super(new Position(Constants.BOARD_ROWS - 1, 4), image);
        this.listener = listener;
    }

    public void moveLeft() {
        position = new Position(position.row, position.column - 1);
    }

    public void moveRight() {
        position = new Position(position.row, position.column + 1);
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
        if (position.column > 4 || position.column < 3) {
            listener.onCarCrash();
        }
    }
}
