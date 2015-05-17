package co.naughtyspirit.shapedrawer.shapes;

import android.content.Context;
import android.graphics.Canvas;

import co.naughtyspirit.shapedrawer.ShapeManager;

/**
 * * Created by Seishin <atanas@naughtyspirit.co>
 * * on 5/17/15.
 * *
 * * NaughtySpirit 2015
 */
public class Oval extends Shape {

    public Oval(Context context) {
        super(context);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle(width / 2, height / 2, 100, paint);

        super.draw(canvas);
    }
}
