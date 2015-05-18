package ns.com.todolist;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 5/18/15.
 */
public class TaskWrapper {

    private final Task task;

    private TaskWrapper(Task task) {
        this.task = task;
    }

    public static TaskWrapper wrap(Task task) {
        return new TaskWrapper(task);
    }

    public void setTitle(String title) {
        try {
            Method method = task.getClass().getMethod("setTitle", String.class);
            method.invoke(task, title);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getTitle() {
        try {
            Method method = task.getClass().getMethod("getTitle");
            return (String) method.invoke(task);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public void setPriority(int priority) {
        try {
            Method method = getMethod("setPriority", int.class);
            method.invoke(task, priority);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getPriority() {
        try {
            Method method = task.getClass().getMethod("getPriority");
            return (int) method.invoke(task);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void setDate(Date date) {
        try {
            Method method = getMethod("setDate", Date.class);
            method.invoke(task, date);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Date getDate() {
        try {
            Method method = getMethod("getDate");
            return (Date) method.invoke(task);
        } catch (Exception e) {
            e.printStackTrace();
            return new Date();
        }
    }

    public boolean isFinished() {
        try {
            Method method = getMethod("isFinished");
            return (Boolean) method.invoke(task);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Task getTask() {
        return task;
    }

    private Method getMethod(String name, Class<?>... parameterTypes) throws NoSuchMethodException {
        return task.getClass().getMethod(name, parameterTypes);
    }
}
