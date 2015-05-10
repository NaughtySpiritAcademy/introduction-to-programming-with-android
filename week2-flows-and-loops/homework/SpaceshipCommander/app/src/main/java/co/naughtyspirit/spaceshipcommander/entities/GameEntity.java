package co.naughtyspirit.spaceshipcommander.entities;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import co.naughtyspirit.spaceshipcommander.ui.CanvasDrawable;

/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 5/9/15.
 */
public abstract class GameEntity implements CanvasDrawable {
    protected Board.Position position;
    private final Drawable image;

    public GameEntity(Board.Position position, Drawable image) {
        this.position = position;
        this.image = image;
    }

    @Override
    public void onDraw(Canvas canvas, int cellWidth, int cellHeight) {
        int leftPos = (position.column - 1) * cellWidth;
        int topPos = (position.row - 1) * cellHeight;
        int cellX = leftPos + cellWidth / 2;
        int cellY = topPos + cellHeight / 2;
        int cellRadius = cellHeight * 3 / 8;
        image.setBounds(cellX - cellRadius, cellY - cellRadius, cellX + cellRadius, cellY + cellRadius);
        image.draw(canvas);
    }
}
