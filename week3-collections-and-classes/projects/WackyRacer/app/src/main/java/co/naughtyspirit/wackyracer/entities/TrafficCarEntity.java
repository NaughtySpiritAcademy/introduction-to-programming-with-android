package co.naughtyspirit.wackyracer.entities;

import android.graphics.drawable.Drawable;

import java.util.Random;

import co.naughtyspirit.wackyracer.Constants;

/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 5/20/15.
 */
public class TrafficCarEntity extends GameEntity {

    private Random random = new Random();

    public TrafficCarEntity(Position position, Drawable image) {
        super(position, image);
    }

    @Override
    public void onTilesMoved() {
        int newColumn = position.column;
        if (random.nextInt(10) == 0 && position.row < Constants.BOARD_ROWS / 2) {
            newColumn = Constants.ROAD_COLUMNS[random.nextInt(Constants.ROAD_COLUMNS.length)];
        }
        position = new Position(position.row + 1, newColumn);
    }
}
