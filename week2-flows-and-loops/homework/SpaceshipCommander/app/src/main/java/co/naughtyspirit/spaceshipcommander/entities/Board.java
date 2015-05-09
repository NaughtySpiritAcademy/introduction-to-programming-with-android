package co.naughtyspirit.spaceshipcommander.entities;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import co.naughtyspirit.spaceshipcommander.ui.Drawable;

/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 5/9/15.
 */
public class Board implements Drawable {

    public static final int CELL_STROKE_WIDTH = 10;
    private final Paint paint = new Paint();

    private final int rows;
    private final int columns;

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        paint.setStrokeWidth(CELL_STROKE_WIDTH);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
    }

    @Override
    public void onDraw(Canvas canvas, int cellWidth, int cellHeight) {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                canvas.drawRect(col * cellWidth, row * cellHeight, col * cellWidth + cellWidth, row * cellHeight + cellHeight, paint);
            }
        }
    }
}
