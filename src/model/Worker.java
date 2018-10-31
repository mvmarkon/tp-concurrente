package model;
import model.Task.Operation;

public class Worker extends Thread {


    private Bafer bafer;
    private TasksFinalized tasksFinalized;
    private int id;



    public Worker(Bafer bafer, TasksFinalized tasksFinalized, int n){
        this.bafer = bafer;
        this.tasksFinalized = tasksFinalized;
        this.id = n;
    }

    private void processTask( Task ttp) {
        Operation opt = ttp.operation;
        switch (opt) {
            case SET:
                ttp.set();
                break;
            case ADD:
                ttp.add();
                break;
            case SUM:
                ttp.sum();
                break;
            case MUL:
                ttp.mul();
                break;
            case ABS:
                ttp.abs();
                break;
            case MAX:
                ttp.max();
                break;
/*
            case PROD:
                ttp.prod();
                break;
*/
            case ASSIGN:
                ttp.assign();
                break;
            case ASSIGN_MASK:
                ttp.assignWithMask();
                break;
        }

    }




    public void run(){
        while(true) {
            ver();
            System.out.println("Por sacar tarea");
            Task taskBusy = this.bafer.dequeue();
            ver();
            System.out.println("Saco tarea");
            taskBusy.ver();
            this.processTask(taskBusy);
            this.tasksFinalized.addTask(taskBusy);
        }
    }

    public void ver() {
        System.out.println("WORKER: " + this.id);
    }




}