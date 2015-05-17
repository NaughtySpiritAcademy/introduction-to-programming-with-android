package co.naughtyspirit.shapedrawer.shapes;

import android.content.Context;
import android.graphics.Canvas;

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
        super.draw(canvas);

        canvas.drawCircle(width / 2, height / 2, 100, paint);
    }
}
