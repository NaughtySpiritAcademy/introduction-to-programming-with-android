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

    public Triangle(Context context) {
        super(context);

        path = new Path();
    }

    @Override
    public void draw(Canvas canvas) {
        paint.setStyle(Paint.Style.FILL);

        path.lineTo(200, 200);
        path.lineTo(400, 100);
        path.lineTo(400, 200);
        path.lineTo(200, 200);
        path.close();

        canvas.drawPath(path, paint);

        super.draw(canvas);
    }
}
