package co.naughtyspirit.shapedrawer;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import co.naughtyspirit.shapedrawer.views.ShapesListView;
import co.naughtyspirit.shapedrawer.views.SurfaceView;

/**
 * * Created by Seishin <atanas@naughtyspirit.co>
 * * on 5/17/15.
 * *
 * * NaughtySpirit 2015
 */
public class DrawerActivity extends Activity implements OnClickListener {

    private SurfaceView surfaceView;
    private Button nextShape;
    private Button previousShape;
    private TextView tvShapeIndex;
    private TextView tvShapeName;
    private ShapesListView lvShapes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        initUI();
    }

    private void initUI() {
        lvShapes = (ShapesListView) findViewById(R.id.lv_shapes);
        lvShapes.setShapes(ShapeManager.getShapes());

        tvShapeIndex = (TextView) findViewById(R.id.tv_shape_index);
        tvShapeName = (TextView) findViewById(R.id.tv_shape_name);

        surfaceView = (SurfaceView) findViewById(R.id.surfaceview);
        nextShape = (Button) findViewById(R.id.next);
        nextShape.bringToFront();
        nextShape.setOnClickListener(this);

        previousShape = (Button) findViewById(R.id.previous);
        previousShape.bringToFront();
        previousShape.setOnClickListener(this);

        lvShapes.selectShape(0);
        tvShapeName.setText("" + ShapeManager.getShapes().get(0).getTitle());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.next:
                surfaceView.drawNextShape();
                break;

            case R.id.previous:
                surfaceView.drawPreviousShape();
                break;
            default:
                return;
        }

        lvShapes.selectShape(surfaceView.getSelectedIndex());
        tvShapeIndex.setText("" + surfaceView.getSelectedIndex());
        tvShapeName.setText("" + ShapeManager.getShapes().get(surfaceView.getSelectedIndex()).getTitle());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ShapeManager.resetSelectedShapes();
    }
}
