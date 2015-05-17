package ns.com.todolist;

import java.util.ArrayList;
import java.util.Date;

import ns.com.todolist.base.BaseTask;
import ns.com.todolist.base.BaseTaskManager;

/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 5/16/15.
 */
public class TasksManager extends BaseTaskManager {
    ArrayList<BaseTask> tasks;

    @Override
    public void initialize() {
        tasks = new ArrayList<>();
        tasks.add(new Task("Meet Mariika", 3, new Date()));
    }

    @Override
    public ArrayList<BaseTask> getTasks() {
        return tasks;
    }

    @Override
    public void addItem(BaseTask task) {
        tasks.add(task);
    }

    @Override
    public void addTaskAtIndex(int index, BaseTask task) {
        if(index <= tasks.size()) {
            tasks.add(index, task);
        }
    }

    @Override
    public void removeTask(int index) {
        tasks.remove(index);
    }

    @Override
    public BaseTask getTask(int index) {
        return tasks.get(index);
    }

    @Override
    public void setTask(int index, BaseTask item) {
        tasks.set(index, item);
    }

    @Override
    public void markTaskAsFinished(BaseTask task) {
        task.setIsFinished(true);
    }
}
