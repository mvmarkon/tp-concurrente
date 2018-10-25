package model;

public class ConcurVector extends SeqVector{

    private double[] elements;
    int dimension;
    int threads;
    Bafer buf;
    int[] balancedData;


    public ConcurVector(int dim, int threads){
        super(dim);
        dimension = dim;
        threads = threads;
        //elements = new double[dim];

    }


    public void setBalancedData(int[] balance){
        this.balancedData = balance;
    }


    public void balance(int dimension, int threads) {
        int carga = (dimension / threads);
        int[] arrayThreads = new int[threads];
        for (int i = 0; i < arrayThreads.length; i++) {
            arrayThreads[i]= carga;
        }
        int resto = dimension % threads;
        for(int i = 0; i < resto; i++) {
            arrayThreads[i] = ++arrayThreads[i];
        }
        this.setBalancedData(arrayThreads);
    }

    /** Pone el valor d en todas las posiciones del vector.
     * @param d, el valor a ser asignado. */
    public void set(double d) {
        for (int i = 0; i < balancedData.length; i =+ balancedData[i]){
            

        }
    }

    /** Suma los valores de este vector con los de otro (uno a uno).
     * @param v, el vector con los valores a sumar.
     * @precondition dimension() == v.dimension(). */
    public void add(SeqVector v) {
        for (int i = 0; i < dimension(); ++i)
            set(i, get(i) + v.get(i));
    }
}
