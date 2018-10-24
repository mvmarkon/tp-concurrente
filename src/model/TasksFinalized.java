package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mariano on 24/10/18.
 */
public class TasksFinalized {


    private List<Task> finalized = new ArrayList<Task>();



    public synchronized void addTask(Task task){
        this.finalized.add(task);


    }


}
