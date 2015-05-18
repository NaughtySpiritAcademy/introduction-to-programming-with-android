package ns.com.todolist;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 5/16/15.
 */
public class ToDoListAdapter extends BaseAdapter {
    ArrayList<Task> tasks = new ArrayList<>();
    Context context;
    TaskManager tasksManager;

    public ToDoListAdapter(Context context, TaskManager tasksManager) {
        this.context = context;
        this.tasks = tasksManager.getTasks();
        this.tasksManager = tasksManager;
    }

    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public Task getItem(int i) {
        return tasks.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.list_item, null);
        }

        final Task task = getItem(position);
        if (task.isFinished()) {
            convertView.setBackgroundColor(context.getResources().getColor(R.color.task_done));
        }

        TextView titleView = (TextView) convertView.findViewById(R.id.title);
        titleView.setText(task.getTitle());

        TextView priorityView = (TextView) convertView.findViewById(R.id.priority);
        priorityView.setText(task.getPriority() + "");

        TextView dateView = (TextView) convertView.findViewById(R.id.date);
        dateView.setText(dateToString(task.getDate()));

        ImageButton remove = (ImageButton) convertView.findViewById(R.id.remove);
        ImageButton edit = (ImageButton) convertView.findViewById(R.id.edit);
        ImageButton done = (ImageButton) convertView.findViewById(R.id.done);

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeTask(position);
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TaskDialog dialog = new TaskDialog(context, ToDoListAdapter.this, task);
                dialog.setTitle("Edit item");
                dialog.taskIndex = position;
                dialog.isEdit = true;
                dialog.show();
            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tasksManager.markTaskAsFinished(task);
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    public void addItem(Task task) {
        tasksManager.addItem(task);
        notifyDataSetChanged();
    }

    public void addItemAtIndex(int index, Task task) {
        tasksManager.addTaskAtIndex(index, task);
        notifyDataSetChanged();
    }

    public void removeTask(int index) {
        tasksManager.removeTask(index);
        notifyDataSetChanged();
    }

    public void setTask(int itemIndex, Task task) {
        tasksManager.setTask(itemIndex, task);
        notifyDataSetChanged();
    }

    private String dateToString(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return simpleDateFormat.format(date);
    }
}
