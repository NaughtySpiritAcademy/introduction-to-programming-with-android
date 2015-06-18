package co.naughtyspirit.todolist;

import java.util.ArrayList;

/**
 * Created by nmp on 15-6-11.
 */
public class TaskManager {
    private static ArrayList<String> tasks = new ArrayList<>();
    public static void addTask(String task) {
        tasks.add(task);
    }

    public static void deleteTask(int position) {
        tasks.remove(position);
    }

    public static ArrayList<String> getTasks() {
        return tasks;
    }
}
