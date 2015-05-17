package co.naughtyspirit.shapedrawer;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import co.naughtyspirit.shapedrawer.shapes.Shape;
import co.naughtyspirit.shapedrawer.shapes.Triangle;

/**
 * * Created by Seishin <atanas@naughtyspirit.co>
 * * on 5/17/15.
 * *
 * * NaughtySpirit 2015
 */
public class DrawerActivity extends Activity {

    private SurfaceView surfaceView;

    private ArrayList<Shape> shapes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        initUI();
    }

    private void initUI() {
        surfaceView = new SurfaceView(this);

        shapes.add(new Triangle(this));

        surfaceView.addShapes(shapes);

        addContentView(surfaceView, new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
    }
}
