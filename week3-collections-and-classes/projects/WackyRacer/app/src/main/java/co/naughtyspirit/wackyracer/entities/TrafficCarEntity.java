package co.naughtyspirit.wackyracer.entities;

import android.graphics.drawable.Drawable;

/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 5/20/15.
 */
public class TrafficCarEntity extends GameEntity {
    public TrafficCarEntity(Position position, Drawable image) {
        super(position, image);
    }

    @Override
    public void onTilesMoved() {
        position = new Position(position.row + 1, position.column);
    }
}
