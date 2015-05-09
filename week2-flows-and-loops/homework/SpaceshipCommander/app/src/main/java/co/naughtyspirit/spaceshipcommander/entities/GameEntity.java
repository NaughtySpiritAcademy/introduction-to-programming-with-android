package co.naughtyspirit.spaceshipcommander.entities;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import co.naughtyspirit.spaceshipcommander.ui.CanvasDrawable;

/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 5/9/15.
 */
public abstract class GameEntity implements CanvasDrawable {
    protected int row;
    protected int column;
    private final Drawable image;

    public GameEntity(int row, int column, Drawable image) {
        this.row = row;
        this.column = column;
        this.image = image;
    }

    @Override
    public void onDraw(Canvas canvas, int cellWidth, int cellHeight) {
        int leftPos = (column - 1) * cellWidth;
        int topPos = (row - 1) * cellHeight;
        int cellX = leftPos + cellWidth / 2;
        int cellY = topPos + cellHeight / 2;
        int cellRadius = cellHeight * 3 / 8;
        image.setBounds(cellX - cellRadius, cellY - cellRadius, cellX + cellRadius, cellY + cellRadius);
        image.draw(canvas);
    }
}
