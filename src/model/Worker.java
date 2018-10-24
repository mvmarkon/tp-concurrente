
public class Worker extends Thread {


    private Bafer bafer;



    public Worker(Bafer bafer){
        this.bafer = bafer;
    }




    public void run(){
        while (!this.bafer.isEmpty()){  // o directamente TRUE
            TaskTP taskBusy = this.bafer.dequeue();
            //  aca tenemos que procesar la tarea, no se si con el strategy q hablamos o algo asi..
        }
    }






}