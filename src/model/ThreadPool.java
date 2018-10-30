package model;

import java.util.ArrayList;
import java.util.List;

public class ThreadPool {

    private int threads;
    private int load;
    private List<Worker> workers = new ArrayList<>();


    public ThreadPool(int threads, int load){
        this.threads = threads;
        this.load = load;
    }

    public void createWorkers(Bafer taskBafer, TasksFinalized completedtasks) {
        for (int i = 0; i < threads; i++) {
            Worker worker = new Worker(taskBafer, completedtasks);
            this.workers.add(worker);
            worker.start();
        }
    }
}
