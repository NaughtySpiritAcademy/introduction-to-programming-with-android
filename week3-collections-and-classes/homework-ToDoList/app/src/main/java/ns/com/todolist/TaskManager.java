package ns.com.todolist;

import java.util.ArrayList;

/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 5/16/15.
 */
public class TaskManager {
    ArrayList<Task> tasks;

    public void initialize() {
    }

    public ArrayList<Task> getTasks() {
        return null;
    }

    public void addItem(Task task) {
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
    }

    public void markTaskAsFinished(Task task) {
    }
}