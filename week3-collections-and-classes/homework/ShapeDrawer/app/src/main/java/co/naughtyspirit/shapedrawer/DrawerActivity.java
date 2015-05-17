package co.naughtyspirit.shapedrawer;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import co.naughtyspirit.shapedrawer.shapes.Oval;
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
    private Button next;
    private Button previous;

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
        shapes.add(new Oval(this));

        surfaceView.addShapes(shapes);

        ((RelativeLayout) findViewById(R.id.layout)).addView(surfaceView, new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        next = (Button) findViewById(R.id.next);
        next.bringToFront();
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                surfaceView.showNext();
            }
        });

        previous = (Button) findViewById(R.id.previous);
        previous.bringToFront();
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                surfaceView.showPrevious();
            }
        });
    }
}
