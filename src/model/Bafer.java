public class Bafer {

    int size;
    private TaskTP[] data;
    private int begin = 0, end = 0;

    public int getSize() {
        return this.size;
    }


    public Bafer(int n) {
        this.size = n;
        this.data = new TaskTP[n];
    }


    public synchronized void queue (TaskTP task) {
        while ( isFull ()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.data[this.end] = task;
        this.end  = next(this.end);
        notifyAll ();
    }


    public synchronized TaskTP dequeue () {
        while ( isEmpty ()) {
            try {
                wait ();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        TaskTP result = this.data [this.begin];
        this.begin = next(this.begin);
        notifyAll ();
        return result ;
    }

    public boolean isEmpty () { return this.begin == this.end ; }
    public boolean isFull () { return this.end == this.size; }
    public int next ( int i) { return (i + 1); }
}
