package model;

import java.util.ArrayList;
import java.util.List;

public class ThreadPool {

    private int threads;
    private List<Worker> workers = new ArrayList<>();


    public ThreadPool(int threads){
        this.threads = threads;
    }

    public void createWorkers(Bafer taskBafer, TasksFinalized completedtasks) {
        for (int i = 0; i < threads; i++) {
            Worker worker = new Worker(taskBafer, completedtasks, i);
            System.out.print("Se creo: ");
            worker.ver();
            this.workers.add(worker);
            worker.start();
        }
    }
}
