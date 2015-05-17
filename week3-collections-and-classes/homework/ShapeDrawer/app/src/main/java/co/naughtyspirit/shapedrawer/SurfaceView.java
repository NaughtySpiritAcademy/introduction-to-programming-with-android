package co.naughtyspirit.shapedrawer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;

import java.util.ArrayList;

import co.naughtyspirit.shapedrawer.shapes.Shape;

/**
 * * Created by Seishin <atanas@naughtyspirit.co>
 * * on 5/17/15.
 * *
 * * NaughtySpirit 2015
 */
public class SurfaceView extends android.view.SurfaceView {

    private ArrayList<Shape> shapes = new ArrayList<>();
    private int currentIdx = 0;

    public SurfaceView(Context context) {
        super(context);

        setBackgroundColor(Color.WHITE);
    }

    public void addShapes(ArrayList<Shape> shapes) {
        this.shapes = shapes;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        shapes.get(currentIdx).draw(canvas);

    }

    public void showNext() {
        invalidate();

        if (currentIdx < shapes.size() - 1) {
            currentIdx += 1;
        }
    }

    public void showPrevious() {
        invalidate();

        if (currentIdx > 0) {
            currentIdx -= 1;
        }
    }
}
