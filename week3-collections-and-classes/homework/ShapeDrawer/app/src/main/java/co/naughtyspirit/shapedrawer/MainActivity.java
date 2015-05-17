package co.naughtyspirit.shapedrawer;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import co.naughtyspirit.shapedrawer.shapes.Oval;
import co.naughtyspirit.shapedrawer.shapes.Triangle;


public class MainActivity extends Activity implements View.OnClickListener {

    ImageView ivCircle;
    ImageView ivTriangle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivCircle = (ImageView) findViewById(R.id.iv_circle);
        ivTriangle = (ImageView) findViewById(R.id.iv_triangle);
        ivCircle.setOnClickListener(this);
        ivTriangle.setOnClickListener(this);
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
        switch (view.getId()) {
            case R.id.iv_circle:
                ShapeManager.addShape(new Oval(this));
                break;
            case R.id.iv_triangle:
                ShapeManager.addShape(new Triangle(this));
                break;
        }
    }
}
