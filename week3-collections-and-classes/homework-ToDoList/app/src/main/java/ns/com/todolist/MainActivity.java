package ns.com.todolist;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;


public class MainActivity extends Activity {

    ListView list;
    Button addBtn;

    TaskManager tasksManager;
    ToDoListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DialogHelper.initialize(this);

        list = (ListView) findViewById(R.id.list);
        addBtn = (Button) findViewById(R.id.add);
        tasksManager = new TaskManager();
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
        TaskDialog dialog = new TaskDialog(this, adapter, TaskWrapper.wrap(new Task()));
        dialog.show();
    }


}
