package co.naughtyspirit.spaceshipcommander.ui;

import android.graphics.Canvas;

/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 5/9/15.
 */
public interface CanvasDrawable {
    void onDraw(Canvas canvas, int cellWidth, int cellHeight);
}
