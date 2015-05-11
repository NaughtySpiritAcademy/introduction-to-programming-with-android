package co.naughtyspirit.spaceshipcommander.ui;

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
    private int cellWidth;
    private int cellHeight;

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
            canvasDrawable.onDraw(canvas, cellWidth, cellHeight);
        }
    }

    public void clearDrawables() {
        drawables.clear();
    }

    public void setCellHeight(int cellHeight) {
        this.cellHeight = cellHeight;
    }

    public void setCellWidth(int cellWidth) {
        this.cellWidth = cellWidth;
    }
}
