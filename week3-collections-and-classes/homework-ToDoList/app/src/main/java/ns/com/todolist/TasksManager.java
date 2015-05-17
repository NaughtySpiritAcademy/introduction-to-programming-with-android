package ns.com.todolist;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 5/16/15.
 */
public class TasksManager {
    ArrayList<Task> tasks;

    public void initialize() {
        tasks = new ArrayList<>();
        tasks.add(new Task("Meet Mariika", 3, new Date()));
    }

    public ArrayList<Task> getItems() {
        return tasks;
    }

    public void addItem(Task task) {
        tasks.add(task);
    }

    public void addTaskAtIndex(int index, Task task) {
        if(index <= tasks.size()) {
            tasks.add(index, task);
        }
    }

    public void removeTask(int index) {
        tasks.remove(index);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public void setTask(int index, Task item) {
        tasks.set(index, item);
    }

    public void markTaskAsFinished(Task task) {
        task.setIsFinished(true);
    }
}
