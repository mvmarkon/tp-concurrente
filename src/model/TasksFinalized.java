package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mariano on 24/10/18.
 */
public class TasksFinalized {


    private List<TaskTP> finalized = new ArrayList<TaskTP>();



    public synchronized void addTask(TaskTP task){
        this.finalized.add(task);


    }


}
