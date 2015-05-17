package co.naughtyspirit.shapedrawer.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import co.naughtyspirit.shapedrawer.R;
import co.naughtyspirit.shapedrawer.ShapeManager;
import co.naughtyspirit.shapedrawer.shapes.Shape;


public class ShapeAdapter extends RecyclerView.Adapter<ShapeAdapter.ViewHolder>{
    private List<Shape> shapes;
    private Context context;

    public ShapeAdapter(Context context, List<Shape> shapes) {
        this.shapes = shapes;
        this.context = context;
    }

    public void updateShapes(List<Shape> shapes) {
        this.shapes = shapes;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        final LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        final View sView = mInflater.inflate(R.layout.list_item_shape, parent, false);
        return new ViewHolder(sView);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        viewHolder.ivShape.setOnClickListener(null);
        if(shapes.get(i).getClass().getSimpleName().toLowerCase().contains("triangle")) {
            viewHolder.ivShape.setImageResource(R.drawable.ic_triangle);
        } else {
            viewHolder.ivShape.setImageResource(R.drawable.ic_circle);
        }
        viewHolder.ivShape.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShapeManager.removeShape(i);
                updateShapes(ShapeManager.getShapes());
            }
        });

        viewHolder.tvIndex.setText(i + "");
    }

    @Override
    public int getItemCount() {
        return shapes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivShape;
        TextView tvIndex;
        public ViewHolder(View itemView) {
            super(itemView);
            ivShape = (ImageView) itemView.findViewById(R.id.iv_shape);
            tvIndex = (TextView) itemView.findViewById(R.id.tv_index);
        }
    }

}