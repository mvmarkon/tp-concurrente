package model;

public class Barrier {
    int workers = 0;
    int completed = 0;
    int tasks = 0;
    int started = 0;

    public synchronized void setWorkers(int wrkrs) {
        this.workers = wrkrs;
    }

    public synchronized void setTasks(int tsks) {
        this.tasks = tsks;
    }
    public synchronized void canProceed() {
        while(completed < workers) {
            try { wait(); }
            catch (InterruptedException e) {}
        }
        completed = 0;
    }

    public synchronized void taskdone() {
        completed++;
        notifyAll();
    }
   /* public synchronized void workStarted() {
        started++;
        notifyAll();
    }*/

    /*public void allTaskscreated() {
        while(started < tasks) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        started = 0;
    }*/
}
