package model;

import java.util.ArrayList;
import java.util.List;

public class ThreadPool {


    private int threads;
    private int load;
    private List<Worker> workers = new ArrayList<Worker>();


    public ThreadPool(int threads, int load){
        this.threads = threads;
        this.load = load;
    }








}
