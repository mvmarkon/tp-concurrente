package model;

/**
 * Created by mariano on 24/10/18.
 */
public class TasksFinalized {

    private int tasks;
    private int finishedTasks = 0;
    private Task[] finalized;

    public TasksFinalized() {
    }

    public synchronized void setTasks(int tsks) {
        this.tasks = tsks;
        finalized = new Task[tasks];
    }

    public synchronized void addTask(Task task){
      //  finishedTasks++;
        finalized[task.getPosition()] = task;
        notifyAll();
    }

    /*public synchronized void allTaskCompleted() {
        while(finishedTasks < tasks) {
            try { wait(); }
            catch (InterruptedException e) {}
        }
        finishedTasks = 0;
    }
*/
    public synchronized Task[] getFinalized() {
        return finalized;
    }
}
