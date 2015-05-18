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
import java.util.List;

/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 5/16/15.
 */
public class ToDoListAdapter extends BaseAdapter {
    List<TaskWrapper> tasks = new ArrayList<>();
    Context context;
    TaskManager taskManager;

    public ToDoListAdapter(Context context, TaskManager taskManager) {
        this.context = context;

        List<Task> managerTasks = taskManager.getTasks();
        if (managerTasks == null) {
            managerTasks = new ArrayList<>();
        }
        for (Task task : managerTasks) {
            tasks.add(TaskWrapper.wrap(task));
        }
        this.taskManager = taskManager;
    }

    @Override
    public int getCount() {
        return tasks == null ? 0 : tasks.size();
    }

    @Override
    public TaskWrapper getItem(int i) {
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

        final TaskWrapper task = getItem(position);
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
                taskManager.markTaskAsFinished(task.getTask());
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    public void addItem(Task task) {
        taskManager.addItem(task);
        notifyDataSetChanged();
    }

    @Override
    public void notifyDataSetChanged() {
        syncTasks();
        super.notifyDataSetChanged();
    }

    private void syncTasks() {
        tasks.clear();
        List<Task> managerTasks = taskManager.getTasks();
        if (managerTasks == null) {
            managerTasks = new ArrayList<>();
        }
        for (Task task : managerTasks) {
            tasks.add(TaskWrapper.wrap(task));
        }
    }

    public void addItemAtIndex(int index, Task task) {
        taskManager.addTaskAtIndex(index, task);
        notifyDataSetChanged();
    }

    public void removeTask(int index) {
        taskManager.removeTask(index);
        notifyDataSetChanged();
    }

    public void setTask(int itemIndex, Task task) {
        taskManager.setTask(itemIndex, task);
        notifyDataSetChanged();
    }

    private String dateToString(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return simpleDateFormat.format(date);
    }
}
