package co.naughtyspirit.spaceshipcommander.entities;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

import co.naughtyspirit.spaceshipcommander.ui.CanvasDrawable;

/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 5/9/15.
 */
public class Board implements CanvasDrawable {

    public static final int CELL_STROKE_WIDTH = 10;
    private final Paint paint = new Paint();

    private final int rows;
    private final int columns;
    private final Drawable background;

    public Board(int rows, int columns, Drawable background) {
        this.rows = rows;
        this.columns = columns;
        this.background = background;

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLUE);
    }

    @Override
    public void onDraw(Canvas canvas, int cellWidth, int cellHeight) {

        background.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        background.draw(canvas);

        paint.setStrokeWidth(cellHeight / 30);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                canvas.drawRect(col * cellWidth, row * cellHeight, col * cellWidth + cellWidth, row * cellHeight + cellHeight, paint);
            }
        }
    }
}
