package ns.com.todolist.factories;

import java.util.ArrayList;
import java.util.Date;

import ns.com.todolist.TasksManager;
import ns.com.todolist.base.BaseTask;
import ns.com.todolist.base.BaseTaskManager;

public class TaskManagerFactory {
    public static BaseTaskManager getTaskManager() {
        return new TasksManager();
    }
}
