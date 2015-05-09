package co.naughtyspirit.spaceshipcommander.entities;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 5/9/15.
 */
public class Obstacle extends GameEntity {
    private final Paint paint = new Paint();

    public Obstacle(int row, int column) {
        super(row, column);
        paint.setColor(Color.GREEN);
    }

    @Override
    public void onDraw(Canvas canvas, int cellWidth, int cellHeight) {
        int radius = canvas.getWidth() / 15;
        canvas.drawCircle(column * cellWidth - (cellWidth / 2), row * cellHeight - (cellHeight / 2), radius, paint);
    }
}
