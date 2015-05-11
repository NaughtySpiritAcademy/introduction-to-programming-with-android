package co.naughtyspirit.spaceshipcommander.entities;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

import co.naughtyspirit.spaceshipcommander.Constants;
import co.naughtyspirit.spaceshipcommander.ui.CanvasDrawable;
import co.naughtyspirit.spaceshipcommander.ui.CanvasView;

/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 5/9/15.
 */
public class Board implements CanvasDrawable {

    private final Paint paint = new Paint();

    private final Drawable background;
    private final CanvasView canvasView;
    private final Size size;
    private final List<GameEntity> gameEntities = new ArrayList<>();

    public Board(int width, int height, Size size, Drawable background, CanvasView canvasView) {
        this.size = size;
        this.background = background;
        this.canvasView = canvasView;
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLUE);
        canvasView.clearDrawables();
        int cellWidth = width / size.columns;
        int cellHeight = height / size.rows;
        canvasView.setCellWidth(cellWidth);
        canvasView.setCellHeight(cellHeight);
        canvasView.add(this);
        reset();
    }

    @Override
    public void onDraw(Canvas canvas, int cellWidth, int cellHeight) {

        background.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        background.draw(canvas);

        paint.setStrokeWidth(cellHeight / Constants.CELL_STROKE_WIDTH_DENOMINATOR);

        for (int row = 0; row < size.rows; row++) {
            for (int col = 0; col < size.columns; col++) {
                canvas.drawRect(col * cellWidth, row * cellHeight, col * cellWidth + cellWidth, row * cellHeight + cellHeight, paint);
            }
        }
    }

    public void add(GameEntity entity) {
        canvasView.add(entity);
        gameEntities.add(entity);
    }

    public void checkForInvalidPosition(Ship ship) {
        ship.checkForBoardBounds(size);
        ship.checkForEntityCollisions(gameEntities);
        reset();
    }

    public void reset() {
        canvasView.invalidate();
    }

    public static class Size {
        public Size(int rows, int columns) {
            this.rows = rows;
            this.columns = columns;
        }

        public final int rows;
        public final int columns;
    }

    public static class Position {
        public Position(int row, int column) {
            this.row = row;
            this.column = column;
        }

        public final int row;
        public final int column;
    }
}
