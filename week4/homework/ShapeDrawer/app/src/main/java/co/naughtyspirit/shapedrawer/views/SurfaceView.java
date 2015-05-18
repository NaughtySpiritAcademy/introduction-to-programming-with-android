package co.naughtyspirit.shapedrawer.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.List;

import co.naughtyspirit.shapedrawer.ShapeManager;
import co.naughtyspirit.shapedrawer.shapes.Shape;

/**
 * * Created by Seishin <atanas@naughtyspirit.co>
 * * on 5/17/15.
 * *
 * * NaughtySpirit 2015
 */
public class SurfaceView extends android.view.SurfaceView {

    private List<Shape> shapes = new ArrayList<>();
    private int currentIdx = 0;

    public SurfaceView(Context context) {
        super(context);

        setBackgroundColor(Color.WHITE);

        shapes = ShapeManager.getShapes();
    }

    public SurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBackgroundColor(Color.WHITE);

        shapes = ShapeManager.getShapes();
    }

    public SurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setBackgroundColor(Color.WHITE);

        shapes = ShapeManager.getShapes();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        shapes.get(currentIdx).draw(canvas);
    }

    public void drawNextShape() {
        invalidate();

        if (currentIdx < shapes.size() - 1) {
            currentIdx += 1;
        }
    }

    public void drawPreviousShape() {
        invalidate();

        if (currentIdx > 0) {
            currentIdx -= 1;
        }
    }

    public int getSelectedIndex() {
        return currentIdx;
    }
}
