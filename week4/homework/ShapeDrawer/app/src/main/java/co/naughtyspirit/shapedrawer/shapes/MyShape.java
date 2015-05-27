package co.naughtyspirit.shapedrawer.shapes;

import android.content.Context;
import android.graphics.Canvas;

//You should finish this class to get the example working
public class MyShape extends Shape{
    public MyShape(Context context, String title) {
        super(context, "MyShape");
    }

    @Override
    public void draw(Canvas canvas) {
        int centerX = width / 2;
        int centerY = height / 2;
        canvas.drawRect(centerX, centerY, centerX + 200, centerY + 200, paint);
    }
}
