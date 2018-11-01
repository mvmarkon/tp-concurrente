package model;

import java.util.ArrayList;
import java.util.List;

public class ThreadPool {

    private int threads;
    private List<Worker> workers = new ArrayList<>();


    public ThreadPool(int threads){
        this.threads = threads;
    }

    public synchronized void createWorkers(Bafer taskBafer, TasksFinalized completedtasks, Barrier barrier) {
        for (int i = 0; i < threads; i++) {
            Worker worker = new Worker(taskBafer, completedtasks, barrier, i);

//            worker.ver();
            this.workers.add(worker);
            worker.start();
        }
        System.out.println("CREARON LOS WORKERS");
    }
}
