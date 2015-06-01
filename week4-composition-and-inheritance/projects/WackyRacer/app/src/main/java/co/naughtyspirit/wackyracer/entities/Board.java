package co.naughtyspirit.wackyracer.entities;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.List;

import co.naughtyspirit.wackyracer.utils.Constants;
import co.naughtyspirit.wackyracer.ui.CanvasDrawable;
import co.naughtyspirit.wackyracer.ui.CanvasView;

/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 5/9/15.
 */
public class Board implements CanvasDrawable {

    private final Paint paint = new Paint();

    private final CanvasView canvasView;
    private final int width;
    private final int height;
    private final Size size;
    private final List<GameEntity> gameEntities = new ArrayList<>();

    private boolean shouldStartFromTop = true;
    private Paint backgroundPaint = new Paint();
    private Paint roadPaint = new Paint();
    private Paint roadLinePaint = new Paint();

    public Board(int width, int height, Size size, CanvasView canvasView) {
        this.width = width;
        this.height = height;
        this.size = size;
        this.canvasView = canvasView;
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLUE);
        int tileWidth = width / size.columns;
        int tileHeight = height / size.rows;
        canvasView.setTileWidth(tileWidth);
        canvasView.setTileHeight(tileHeight);
        canvasView.add(this);

        backgroundPaint.setColor(Color.parseColor(Constants.BACKGROUND_COLOR));
        roadPaint.setColor(Color.DKGRAY);
        roadLinePaint.setColor(Color.WHITE);
        redraw();
    }

    @Override
    public void onDraw(Canvas canvas, int tileWidth, int tileHeight) {

        for (int row = 0; row < size.rows; row++) {
            for (int col = 0; col < size.columns; col++) {
                canvas.drawRect(col * tileWidth, row * tileHeight, col * tileWidth + tileWidth, row * tileHeight + tileHeight, backgroundPaint);
            }
        }

        for (int row = 0; row < size.rows; row++) {
            for (int roadColumn : Constants.ROAD_COLUMNS) {
                roadColumn -= 1;
                canvas.drawRect(roadColumn * tileWidth, row * tileHeight, roadColumn * tileWidth + tileWidth, row * tileHeight + tileHeight, roadPaint);
            }
        }

        float roadLineHeight = height / 10;
        float roadLineWidth = width / 50;
        float roadLineHeightWithPadding = roadLineHeight * 2;
        float usedHeight = 0;
        if (!shouldStartFromTop) {
            usedHeight += roadLineHeight;
        }
        do {
            canvas.drawRect(width / 2 - roadLineWidth / 2, usedHeight - roadLineHeight / 2, width / 2 + roadLineWidth / 2, usedHeight + roadLineHeight / 2, roadLinePaint);
            usedHeight += roadLineHeightWithPadding;
        } while (usedHeight < height);
        shouldStartFromTop = !shouldStartFromTop;

    }

    public void checkForCollisions(VehicleEntity vehicle) {
        vehicle.checkForCollisions(gameEntities);
    }

    public void add(GameEntity entity) {
        canvasView.add(entity);
        gameEntities.add(entity);
    }

    public void onGameOver() {
        gameEntities.clear();
        canvasView.clearDrawables();
        redraw();
    }

    public void redraw() {
        canvasView.invalidate();
    }

    public void moveTiles() {
        for (GameEntity entity : gameEntities) {
            entity.onTilesMoved();
        }
        removeNonVisibleEntities();
    }

    private void removeNonVisibleEntities() {
        List<GameEntity> entitiesToRemove = new ArrayList<>();
        for (GameEntity entity : gameEntities) {
            int row = entity.position.row;
            if (row > Constants.BOARD_ROWS) {
                entitiesToRemove.add(entity);
                canvasView.remove(entity);
            }
        }
        gameEntities.removeAll(entitiesToRemove);
    }

    public List<GameEntity> getGameEntities() {
        return gameEntities;
    }

    public static class Size {
        public Size(int rows, int columns) {
            this.rows = rows;
            this.columns = columns;
        }

        public final int rows;
        public final int columns;
    }

}
