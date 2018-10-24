package model;

public class Worker extends Thread {


    private Bafer bafer;
    private TasksFinalized tasksFinalized;



    public Worker(Bafer bafer, TasksFinalized tasksFinalized){
        this.bafer = bafer;
        this.tasksFinalized = tasksFinalized;
    }




    public void run(){
        while (!this.bafer.isEmpty()){  // o directamente TRUE  ??
            TaskTP taskBusy = this.bafer.dequeue();
            //  aca tenemos que procesar la tarea, no se si con el strategy q hablamos o algo asi..
            this.tasksFinalized.addTask(taskBusy);
        }
    }






}