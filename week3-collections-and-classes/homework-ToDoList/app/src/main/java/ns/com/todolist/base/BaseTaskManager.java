
package ns.com.todolist.base;

import java.util.ArrayList;
import java.util.Date;

import ns.com.todolist.Task;

/**
 * Created by nmp on 15-5-17.
 */
public abstract class BaseTaskManager {
    public abstract void initialize();

    public abstract ArrayList<BaseTask> getTasks();

    public abstract void addItem(BaseTask task);

    public abstract void addTaskAtIndex(int index, BaseTask task);

    public abstract void removeTask(int index);

    public abstract BaseTask getTask(int index);

    public abstract void setTask(int index, BaseTask item);

    public abstract void markTaskAsFinished(BaseTask task);
}
