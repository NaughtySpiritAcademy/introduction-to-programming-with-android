package ns.com.todolist.factories;

import ns.com.todolist.TasksManager;
import ns.com.todolist.base.BaseTaskManager;

public class TaskManagerFactory {
    public static BaseTaskManager getTaskManager() {
        return new TasksManager();
    }
}
