package co.naughtyspirit.shapedrawer.views;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.List;

import co.naughtyspirit.shapedrawer.R;
import co.naughtyspirit.shapedrawer.adapters.ShapeAdapter;
import co.naughtyspirit.shapedrawer.shapes.Shape;

public class ShapesListView extends RelativeLayout {

    private List<Shape> shapes;
    private RecyclerView recyclerView;
    private ShapeAdapter adapter;

    public ShapesListView(Context context) {
        super(context);
        if(!isInEditMode()) {
            init(context);
        }
    }

    public ShapesListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if(!isInEditMode()) {
            init(context);
        }
    }

    public ShapesListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if(!isInEditMode()) {
            init(context);
        }
    }

    public void setShapes(List<Shape> shapes) {
        this.shapes = shapes;
        resetAdapter();
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.view_shapes_list, this, true);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context.getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        resetAdapter();
    }

    private void resetAdapter() {
        if(shapes== null) {
            return;
        }
        if(adapter == null) {
            adapter = new ShapeAdapter(getContext(), shapes);
            recyclerView.setAdapter(adapter);
        } else {
            adapter.updateShapes(shapes);
        }
    }
}
