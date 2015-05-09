package co.naughtyspirit.spaceshipcommander.entities;

import android.graphics.drawable.Drawable;

import java.util.List;

import co.naughtyspirit.spaceshipcommander.Command;

/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 5/9/15.
 */
public class Ship extends GameEntity {

    private final ShipCollisionListener collisionListener;
    private final int initialColumn;
    private final int initialRow;

    public Ship(int row, int column, Drawable image, ShipCollisionListener collisionListener) {
        super(row, column, image);
        this.initialRow = row;
        this.initialColumn = column;
        this.collisionListener = collisionListener;
    }

    public void executeCommand(Command command) {
        column += command.columns;
        row += command.rows;
    }

    public boolean checkForCollisions(List<GameEntity> gameEntities) {
        for (GameEntity entity : gameEntities) {
            if (entity.row == row && entity.column == column) {
                if (entity instanceof BlackHole) {
                    collisionListener.onCollisionWithObstacle();
                    return true;
                } else if (entity instanceof Planet) {
                    collisionListener.onCollisionWithPlanet();
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkForBoardBounds(int rows, int columns) {
        if (row <= 0 || row > rows || column <= 0 || column > columns) {
            collisionListener.onCollisionWithBoard();
            return true;
        }
        return false;
    }

    public void reset() {
        this.row = initialRow;
        this.column = initialColumn;
    }
}
