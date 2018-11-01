package model;

public class Bafer {

    int size;
    private Task[] data;
    private int begin = 0, end = -1, count = 0;

    public int getSize() {
        return this.size;
    }


    public Bafer(int n) {
        this.size = n;
        this.data = new Task[n];
    }


    public synchronized void queue (Task task) {
        while ( isFull()) {
            try {
                System.out.println("Se INTENTA ENCOLAR");
                wait();
//                task.ver();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.end = next(this.end);
        this.data[this.end] = task;
        //System.out.println("QUEUED TASK: ");
        //task.ver();
        count++;
        notifyAll();
    }


    public synchronized Task dequeue () {
        while ( isEmpty()) {
            try {
                System.out.println("Se INTENTA DESENCOLAR");
               // ver();
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Task result = this.data [this.begin];
        this.begin = next(this.begin);
        //System.out.print("DEQUEUED");
        //result.ver();
        count--;
        notifyAll();
        return result ;
    }

    public synchronized boolean isEmpty() { return count == 0 ; }
    public synchronized boolean isFull() { return this.end == this.size; }
    public synchronized int next( int i) { return (i+1)%(this.size); }

    /*public static void main(String[] args) {

        int size = 5;
        for (int i = 0; i < 11; i++) {
            int n = ((i+1)%(size));
            System.out.println( " en " + i + " da " +  n);

        }
    }
*/
    public synchronized void ver() {
        System.out.println("BUFER " + begin +" " + end);
    }
}
