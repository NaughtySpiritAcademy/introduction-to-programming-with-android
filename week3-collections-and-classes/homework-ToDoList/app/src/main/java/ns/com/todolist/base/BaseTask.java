package ns.com.todolist.base;

import java.util.Date;

/**
 * Created by nmp on 15-5-17.
 */
public abstract class BaseTask {
    public abstract String getTitle();

    public abstract void setTitle(String title);

    public abstract int getPriority();

    public abstract void setPriority(int priority);

    public abstract Date getDate();

    public abstract void setDate(Date date);

    public abstract boolean isFinished();

    public abstract void setIsFinished(boolean isFinished);
}
