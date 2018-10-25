package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mariano on 24/10/18.
 */
public class TasksFinalized {

    private int tasks;
    private int finishedTasks = 0;
    private List<Task> finalized = new ArrayList<Task>();

    public TasksFinalized(int tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task){
        finishedTasks++;
        finalized.set(task.getPosition(), task);
        notify();
    }

    public synchronized void allTaskCompleted() {
        while ( finishedTasks < tasks) {
            try { wait(); }
            catch (InterruptedException e) {}
        }
        finishedTasks = 0;
    }

    public List<Task> getFinalized() {
        return finalized;
    }
}
