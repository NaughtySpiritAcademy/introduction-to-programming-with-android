package co.naughtyspirit.waitinginlinestack.entities;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import co.naughtyspirit.waitinginlinestack.Constants;
import co.naughtyspirit.waitinginlinestack.ui.CanvasDrawable;

/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 5/16/15.
 */
public class Desk implements CanvasDrawable {
    private final Paint paint = new Paint();
    private final Rect textBounds = new Rect();

    @Override
    public void onDraw(Canvas canvas) {
        float width = canvas.getWidth();
        float height = canvas.getHeight();
        paint.setColor(Color.GREEN);
        float left = width / 15;
        int top = canvas.getHeight() / 6;
        float right = width - (width / 15);
        int bottom = canvas.getHeight() / 4;
        canvas.drawRect(left, top, right, bottom, paint);
        paint.setColor(Color.BLACK);
        paint.setTextSize(canvas.getWidth() / Constants.PERSON_LABEL_SIZE_COEF);
        drawTextCentred(canvas, paint, "EVN  4  LIFE", canvas.getWidth() / 2, (bottom + top) / 2);
    }

    public void drawTextCentred(Canvas canvas, Paint paint, String text, float cx, float cy) {
        paint.getTextBounds(text, 0, text.length(), textBounds);
        canvas.drawText(text, cx - textBounds.exactCenterX(), cy - textBounds.exactCenterY(), paint);
    }
}
