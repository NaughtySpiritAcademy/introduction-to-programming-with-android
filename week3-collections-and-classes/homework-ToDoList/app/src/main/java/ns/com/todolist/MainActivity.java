package ns.com.todolist;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import ns.com.todolist.base.BaseTask;
import ns.com.todolist.base.BaseTaskManager;
import ns.com.todolist.factories.TaskFactory;
import ns.com.todolist.factories.TaskManagerFactory;


public class MainActivity extends Activity {

    ListView list;
    Button addBtn;

    BaseTaskManager tasksManager;
    ToDoListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (ListView) findViewById(R.id.list);
        addBtn = (Button) findViewById(R.id.add);
        tasksManager = TaskManagerFactory.getTaskManager();
        tasksManager.initialize();

        adapter = new ToDoListAdapter(this, tasksManager);
        list.setAdapter(adapter);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddItemDialog();
            }

        });
    }

    private void showAddItemDialog() {
        TaskDialog dialog = new TaskDialog(this, adapter, TaskFactory.getTask());
        dialog.show();
    }


}
