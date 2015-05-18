package ns.com.todolist;

import android.util.Log;

import java.util.Date;

/**
 * Created by Naughty Spirit <hi@naughtyspirit.co>
 * on 5/17/15.
 */
public class Task{
    private String title = "";
    private int priority = 1;
    private Date date = new Date();
    private boolean isFinished = false;

    public Task() {
    }

    public Task(String title, int priority, Date date) {
        this.title = title;
        this.priority = priority;
        this.date = date;
        isFinished = false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setIsFinished(boolean isFinished) {
        this.isFinished = isFinished;
    }

}
