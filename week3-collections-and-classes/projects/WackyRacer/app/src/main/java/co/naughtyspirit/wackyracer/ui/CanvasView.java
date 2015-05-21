package co.naughtyspirit.wackyracer.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 5/9/15.
 */
public class CanvasView extends View {

    private final List<CanvasDrawable> drawables = new ArrayList<>();
    private int tileWidth;
    private int tileHeight;

    public CanvasView(Context context) {
        super(context);
    }

    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void add(CanvasDrawable canvasDrawable) {
        drawables.add(canvasDrawable);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (CanvasDrawable canvasDrawable : drawables) {
            canvasDrawable.onDraw(canvas, tileWidth, tileHeight);
        }
    }

    public void clearDrawables() {
        drawables.clear();
    }

    public void remove(CanvasDrawable canvasDrawable) {
        drawables.remove(canvasDrawable);
    }

    public void setTileWidth(int tileWidth) {
        this.tileWidth = tileWidth;

    }

    public void setTileHeight(int tileHeight) {
        this.tileHeight = tileHeight;
    }
}
