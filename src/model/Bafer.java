package model;

public class Bafer {

    int size;
    private Task[] data;
    private int begin = 0, end = 0;

    public int getSize() {
        return this.size;
    }


    public Bafer(int n) {
        this.size = n;
        this.data = new Task[n];
    }


    public synchronized void queue (Task task) {
        while ( isFull ()) {
            try {
                wait();
                task.ver();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.data[this.end] = task;
        System.out.println("QUEUED TASK: ");
        task.ver();
        this.end  = next(this.end);
        notifyAll ();
    }


    public synchronized Task dequeue () {
        while ( isEmpty ()) {
            try {
                ver();
                wait ();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Task result = this.data [this.begin];
        this.begin = next(this.begin);
        System.out.print("DEQUEUED");
        result.ver();
        notifyAll();
        return result ;
    }

    public synchronized boolean isEmpty () { return this.begin == this.end ; }
    public synchronized boolean isFull () { return this.end == this.size; }
    public synchronized int next ( int i) { return (i+1)%(this.size); }

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
