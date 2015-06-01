package co.naughtyspirit.shapedrawer.shapes;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

/**
 * * Created by Seishin <atanas@naughtyspirit.co>
 * * on 5/17/15.
 * *
 * * NaughtySpirit 2015
 */
public abstract class Shape extends View {
    private boolean isSelected;
    private String title;
    protected Paint paint;
    protected WindowManager wm;
    protected int width;
    protected int height;

    public Shape(Context context, String title) {
        super(context);
        this.title = title;
        paint = new Paint();
        paint.setColor(Color.BLACK);

        wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);

        width = metrics.widthPixels;
        height = metrics.heightPixels;
    }

    @Override
    public boolean isSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
