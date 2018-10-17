public class Bafer {
    int size;
    private double[] data;
    private int begin = 0, end = 0;

    public int getSize() {
        return this.size;
    }

    public Bafer(int n) {
        this.size = n;
        this.data = new double[n];
    }

    public synchronized void queue ( double d) {
        while ( isFull ()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        data[end] = d;
        end  = next ( end );
        notifyAll ();
    }

    public synchronized double dequeue () {
        while ( isEmpty ()) {
            try {
                wait ();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        double result = data [ begin ];
        begin = next ( begin );
        notifyAll ();
        return result ;
    }

    private boolean isEmpty () { return begin == end ; }
    private boolean isFull () { return end == size; }
    private int next ( int i) { return (i +1); }
}
