package model;
import model.Task.Operation;

public class Worker extends Thread {


    private Bafer bafer;
    private TasksFinalized tasksFinalized;



    public Worker(Bafer bafer, TasksFinalized tasksFinalized){
        this.bafer = bafer;
        this.tasksFinalized = tasksFinalized;
    }

    private void processTask( Task ttp) {
        Operation opt= ttp.operation;
        switch (opt) {
            case SET:
                ttp.set();
            case ADD:
                ttp.add();
            case SUM:
                ttp.sum();
            case MUL:
                ttp.mul();
            case ABS:
                ttp.abs();
            case MEAN:
                ttp.mean();
            case NORM:
                ttp.norm();
            case PROD:
                ttp.prod();
        }

    }




    public void run(){
        //while (!this.bafer.isEmpty()){  // o directamente TRUE  ??
        Task taskBusy = this.bafer.dequeue();
        processTask(taskBusy);
        //  aca tenemos que procesar la tarea, no se si con el strategy q hablamos o algo asi..
        this.tasksFinalized.addTask(taskBusy);
    }






}