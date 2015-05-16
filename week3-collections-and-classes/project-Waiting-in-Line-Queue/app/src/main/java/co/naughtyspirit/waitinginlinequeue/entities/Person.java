package co.naughtyspirit.waitinginlinequeue.entities;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import co.naughtyspirit.waitinginlinequeue.Constants;
import co.naughtyspirit.waitinginlinequeue.ui.CanvasDrawable;

/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 5/16/15.
 */
public class Person implements CanvasDrawable {
    private final int number;
    private int position;
    private final Paint paint = new Paint();
    private final Rect textBounds = new Rect();
    private boolean isCurrentClient = false;

    public Person(int number, int position) {
        this.number = number;
        this.position = position;
    }

    @Override
    public void onDraw(Canvas canvas) {
        paint.setColor(Color.BLUE);
        float x = canvas.getWidth() / 2;
        float diameter = canvas.getWidth() / Constants.PERSON_RADIUS_COEF * 2;
        float y = canvas.getHeight() - (canvas.getHeight() / Constants.PERSON_PADDING_COEF) * position - diameter * position;
        if (isCurrentClient) {
            y -= canvas.getHeight() / 15;
        }
        canvas.drawCircle(x, y, diameter / 2, paint);
        paint.setColor(Color.WHITE);
        paint.setTextSize(canvas.getWidth() / Constants.PERSON_LABEL_SIZE_COEF);
        drawTextCentred(canvas, paint, String.valueOf(number), x, y);
    }

    public void drawTextCentred(Canvas canvas, Paint paint, String text, float cx, float cy) {
        paint.getTextBounds(text, 0, text.length(), textBounds);
        canvas.drawText(text, cx - textBounds.exactCenterX(), cy - textBounds.exactCenterY(), paint);
    }

    public void goToDesk() {
        isCurrentClient = true;
    }

    public void moveUp() {
        position++;
    }
}
