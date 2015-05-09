package co.naughtyspirit.spaceshipcommander.entities;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.List;

import co.naughtyspirit.spaceshipcommander.Command;

/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 5/9/15.
 */
public class Ship extends GameEntity {

    private final Paint paint = new Paint();
    private final ShipCollisionListener collisionListener;
    private final int initialColumn;
    private final int initialRow;

    public Ship(int row, int column, ShipCollisionListener collisionListener) {
        super(row, column);
        this.initialRow = row;
        this.initialColumn = column;
        this.collisionListener = collisionListener;
        paint.setColor(Color.RED);
    }

    @Override
    public void onDraw(Canvas canvas, int cellWidth, int cellHeight) {
        int radius = canvas.getWidth() / 15;
        canvas.drawCircle(column * cellWidth - (cellWidth / 2), row * cellHeight - (cellHeight / 2), radius, paint);
    }

    public void executeCommand(Command command) {
        column += command.columns;
        row += command.rows;
    }

    public boolean checkForCollisions(List<GameEntity> gameEntities) {
        for (GameEntity entity : gameEntities) {
            if (entity.row == row && entity.column == column) {
                if (entity instanceof Obstacle) {
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
