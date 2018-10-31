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
            case PROD:
                ttp.prod();
                break;
            case ASSIGN:
                ttp.assign();
                break;
            case ASSIGN_MASK:
                ttp.assignWithMask();
                break;
        }

    }




    public void run(){
        while(true) {        //while (!this.bafer.isEmpty()){  // o directamente TRUE  ??
            Task taskBusy = this.bafer.dequeue();
            this.processTask(taskBusy);
            //  aca tenemos que procesar la tarea, no se si con el strategy q hablamos o algo asi..
            this.tasksFinalized.addTask(taskBusy);
        }
    }






}