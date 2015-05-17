package co.naughtyspirit.shapedrawer;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        initUI();
    }

    private void initUI() {
        surfaceView = new SurfaceView(this);
        ((RelativeLayout) findViewById(R.id.layout)).addView(surfaceView, new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        nextShape = (Button) findViewById(R.id.next);
        nextShape.bringToFront();
        nextShape.setOnClickListener(this);

        previousShape = (Button) findViewById(R.id.previous);
        previousShape.bringToFront();
        previousShape.setOnClickListener(this);
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
        }
    }
}
