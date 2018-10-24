package model;
import model.Task.Operation;

public class Worker extends Thread {


    private Bafer bafer;
    // private TasksFinalized tasksFinalized;



    public Worker(Bafer bafer, TasksFinalized tasksFinalized){
        this.bafer = bafer;
        //this.tasksFinalized = tasksFinalized;
    }

    private void processTask( Task ttp) {
        Operation opt= ttp.operation;
        switch (opt) {
            case ADD:
                add(ttp);
            case SUM:
                sum(ttp);
            case MUL:
                mul(ttp);
            case ABS:
                abs(ttp);
            case MEAN:
                mean(ttp);
            case NORM:
                norm(ttp);
            case PROD:
                prod(ttp);
        }

    }

    /** Suma los valores de este vector con los de otro (uno a uno).
     //@param v, el vector con los valores a sumar.
     * @precondition dimension() == v.dimension().
     *public void add(SeqVector v) {
     *  for (int i = 0; i < dimension(); ++i)
     *       set(i, get(i) + v.get(i));
     *   }
     **/
    private void add(Task ttp) {


    }
    private void sum(Task ttp) {
    }
    private void mul(Task ttp) {
    }
    private void abs(Task ttp) {
    }
    private void mean(Task ttp) {
    }
    private void norm(Task ttp) {
    }
    private void prod(Task ttp) {
    }


    public void run(){
//        while (!this.bafer.isEmpty()){  // o directamente TRUE  ??
            Task taskBusy = this.bafer.dequeue();
            processTask(taskBusy);
            //  aca tenemos que procesar la tarea, no se si con el strategy q hablamos o algo asi..
            //this.tasksFinalized.addTask(taskBusy);
            taskBusy.done();
        }
    }






}