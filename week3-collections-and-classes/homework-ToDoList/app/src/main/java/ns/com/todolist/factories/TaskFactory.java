package ns.com.todolist.factories;

import java.util.Date;

import ns.com.todolist.Task;
import ns.com.todolist.base.BaseTask;

public class TaskFactory {
    public static BaseTask getTask() {
        return new Task();
    }
}
