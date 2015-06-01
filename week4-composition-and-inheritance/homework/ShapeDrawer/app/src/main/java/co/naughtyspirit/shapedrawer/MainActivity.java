package co.naughtyspirit.shapedrawer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import co.naughtyspirit.shapedrawer.dialogs.PositionDialog;
import co.naughtyspirit.shapedrawer.dialogs.PositionDialog.OnPositionSelectedListener;
import co.naughtyspirit.shapedrawer.views.ShapesListView;
import co.naughtyspirit.shapedrawer.wrappers.ShapeManagerWrapper;


public class MainActivity extends Activity implements View.OnClickListener, OnPositionSelectedListener {

    Button btnPreview;
    ImageView ivCircle;
    ImageView ivTriangle;
    ImageView ivMyShape;
    ShapesListView shapesListView;

    String shapeToAdd;

    PositionDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivCircle = (ImageView) findViewById(R.id.iv_circle);
        ivTriangle = (ImageView) findViewById(R.id.iv_triangle);
        ivMyShape = (ImageView) findViewById(R.id.iv_my_shape);
        btnPreview = (Button) findViewById(R.id.preview);
        shapesListView = (ShapesListView) findViewById(R.id.lv_shapes);

        ivCircle.setOnClickListener(this);
        ivTriangle.setOnClickListener(this);
        ivMyShape.setOnClickListener(this);
        btnPreview.setOnClickListener(this);
        dialog = new PositionDialog(this);
        ShapeManagerWrapper.getInstance().initialize();
    }

    @Override
    protected void onResume() {
        super.onResume();
        dialog.setOnPositionSelectedListener(this);

        ShapeManagerWrapper.getInstance().resetSelectedShapes();
        shapesListView.setShapes(ShapeManagerWrapper.getInstance().getShapes());
    }

    @Override
    protected void onStop() {
        super.onStop();
        dialog.removeOnPositionSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        boolean shouldDisplayDialog = true;
        switch (view.getId()) {
            case R.id.iv_circle:
                shapeToAdd = "Oval";
                break;
            case R.id.iv_triangle:
                shapeToAdd = "Triangle";
                break;
            case R.id.iv_my_shape:
                shapeToAdd = "MyShape";
                break;
            case R.id.preview:
                shouldDisplayDialog = false;
                if(ShapeManagerWrapper.getInstance().getShapes() == null || ShapeManagerWrapper.getInstance().getShapes().size() <= 0) {
                    Toast.makeText(this, "Add some shapes!", Toast.LENGTH_LONG).show();
                    return;
                }
                Intent i = new Intent(this, DrawerActivity.class);
                startActivity(i);
                break;
            default:
                shouldDisplayDialog = false;
        }

        if(shouldDisplayDialog) {
            dialog.show();
        }
    }

    @Override
    public void onPositionSelected(int position) {
        ShapeManagerWrapper.getInstance().addShape(this, shapeToAdd, position);
        shapesListView.setShapes(ShapeManagerWrapper.getInstance().getShapes());
    }
}
