package ns.com.todolist.factories;

import ns.com.todolist.Task;
import ns.com.todolist.base.BaseTask;

public class TaskFactory {
    public static BaseTask getTask() {
        return new Task();
    }
}
