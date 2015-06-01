package co.naughtyspirit.wackyracer.ui;

import android.graphics.Canvas;

/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 5/9/15.
 */
public interface CanvasDrawable {
    void onDraw(Canvas canvas, int tileWidth, int tileHeight);
}
