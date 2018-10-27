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
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.data[this.end] = task;
        System.out.println("QUEUED TASK:" + task.operation + ", ID "+task.getPosition());
        this.end  = next(this.end);
        notifyAll ();
    }


    public synchronized Task dequeue () {
        while ( isEmpty ()) {
            try {
                wait ();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Task result = this.data [this.begin];
        //this.data [this.begin] = null;
        System.out.println("DEQUEUED Task"+ result.operation + ", ID: " +result.getPosition());
        this.begin = next(this.begin);
        notifyAll ();
        return result ;
    }

    public boolean isEmpty () { return this.begin == this.end ; }
    public boolean isFull () { return this.end == this.size; }
    public int next ( int i) { return (i+1)%(this.size); }

    /*public static void main(String[] args) {

        int size = 5;
        for (int i = 0; i < 11; i++) {
            int n = ((i+1)%(size));
            System.out.println( " en " + i + " da " +  n);

        }
    }
*/
}
