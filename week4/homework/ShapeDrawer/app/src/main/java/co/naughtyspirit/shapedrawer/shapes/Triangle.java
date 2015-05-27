package co.naughtyspirit.shapedrawer.shapes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

/**
 * * Created by Seishin <atanas@naughtyspirit.co>
 * * on 5/17/15.
 * *
 * * NaughtySpirit 2015
 */
public class Triangle extends Shape {

    private Path path;

    public Triangle(Context context, String name) {
        super(context, name);

        path = new Path();
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    public void draw(Canvas canvas) {
        path.lineTo(width / 2 - 100, height / 2 + 100);
        path.lineTo(width / 2, height / 2 - 200);
        path.lineTo(width / 2 + 100, height / 2 + 100);
        path.lineTo(width / 2 - 100, height / 2 + 100);
        path.close();

        canvas.drawPath(path, paint);

        super.draw(canvas);
    }
}
