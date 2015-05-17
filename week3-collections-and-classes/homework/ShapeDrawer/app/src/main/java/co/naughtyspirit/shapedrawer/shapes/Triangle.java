package co.naughtyspirit.shapedrawer.shapes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * * Created by Seishin <atanas@naughtyspirit.co>
 * * on 5/17/15.
 * *
 * * NaughtySpirit 2015
 */
public class Triangle extends Shape {

    private Paint paint;

    public Triangle(Context context) {
        super(context);

        paint = new Paint();
        paint.setColor(Color.BLACK);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }
}
