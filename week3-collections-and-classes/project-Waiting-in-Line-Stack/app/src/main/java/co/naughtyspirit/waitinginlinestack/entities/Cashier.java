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
public class Cashier implements CanvasDrawable {

    private final Paint paint = new Paint();
    private final Rect textBounds = new Rect();

    @Override
    public void onDraw(Canvas canvas) {
        float width = canvas.getWidth();
        float height = canvas.getHeight();
        float x = canvas.getWidth() / 2;
        float diameter = canvas.getWidth() / Constants.PERSON_RADIUS_COEF * 2;
        int y = canvas.getHeight() / Constants.PERSON_PADDING_COEF * 10;
        paint.setColor(Color.BLACK);
        canvas.drawCircle(x, y, diameter / 2, paint);
        paint.setColor(Color.WHITE);
        paint.setTextSize(canvas.getWidth() / Constants.PERSON_LABEL_SIZE_COEF);
        drawTextCentred(canvas, paint, "$", x, y);
    }

    public void drawTextCentred(Canvas canvas, Paint paint, String text, float cx, float cy) {
        paint.getTextBounds(text, 0, text.length(), textBounds);
        canvas.drawText(text, cx - textBounds.exactCenterX(), cy - textBounds.exactCenterY(), paint);
    }
}
