package co.naughtyspirit.spaceshipcommander.entities;

import co.naughtyspirit.spaceshipcommander.ui.Drawable;

/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 5/9/15.
 */
public abstract class GameEntity implements Drawable {
    protected int row;
    protected int column;

    public GameEntity(int row, int column) {
        this.row = row;
        this.column = column;
    }
}
