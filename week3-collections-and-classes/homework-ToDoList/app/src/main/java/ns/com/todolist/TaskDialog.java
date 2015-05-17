package ns.com.todolist;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.text.InputType;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import ns.com.todolist.base.BaseTask;

/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 5/16/15.
 */
public class TaskDialog extends Dialog implements View.OnClickListener {
    private EditText titleView;
    private EditText priorityView;
    private EditText dateView;

    private BaseTask task;
    public boolean isEdit = false;
    public int taskIndex = 0;

    ToDoListAdapter adapter;

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");

    public TaskDialog(Context context, ToDoListAdapter adapter, BaseTask task) {
        super(context);
        this.adapter = adapter;
        setContentView(R.layout.task_dialog);
        setTitle("Task");
        this.task = task;

        titleView = (EditText) findViewById(R.id.title);
        priorityView = (EditText) findViewById(R.id.priority);
        dateView = (EditText) findViewById(R.id.date);
        dateView.setOnClickListener(this);
        dateView.setInputType(InputType.TYPE_NULL);
        findViewById(R.id.ok).setOnClickListener(this);
        findViewById(R.id.cancel).setOnClickListener(this);
        setDatePicker();

        titleView.setText(task.getTitle());
        priorityView.setText(task.getPriority() + "");
        dateView.setText(dateFormatter.format(task.getDate()));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ok:
                task.setTitle(titleView.getText().toString());
                task.setPriority(Integer.parseInt(priorityView.getText().toString()));
                Date date = new Date();
                try {
                    date = dateFormatter.parse(dateView.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                task.setDate(date);
                if(!isEdit) {
                    adapter.addItem(task);
                } else {
                    adapter.setTask(taskIndex, task);
                }
                dismiss();
                break;
            case R.id.cancel:
                dismiss();
                break;
            case R.id.date:
                datePickerDialog.show();
                break;
        }

    }

    private void setDatePicker() {
        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                dateView.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }
}
