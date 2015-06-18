package co.naughtyspirit.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by nmp on 15-6-15.
 */
public class CustomAdapter extends BaseAdapter {

    ArrayList<String> tasks;
    LayoutInflater inflater;

    public CustomAdapter(Context context,
                         ArrayList<String> tasks) {
        this.tasks = tasks;
        inflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public String getItem(int position) {
        return tasks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        ViewHolder viewHolder;
        if(view == null) {
            view = inflater.inflate(R.layout.item_task,
                    parent, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
            viewHolder.delete.setOnClickListener(null);
        }

        viewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TaskManager.deleteTask(position);
                notifyDataSetChanged();
            }
        });

        String task = tasks.get(position);
        viewHolder.taskName.setText(task);

        return view;
    }

    private static class ViewHolder {
        TextView taskName;
        Button delete;

        public ViewHolder(View view) {
            taskName = (TextView)
                    view.findViewById(R.id.task_name);
            delete = (Button) view.findViewById(R.id.delete);
        }
    }

}
