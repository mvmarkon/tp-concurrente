package model;

import model.Task.Operation;
import java.util.Arrays;

public class ConcurVector extends SeqVector{

    private double[] elements;
    int dimension;
    int threads;
    Bafer buf;
    TasksFinalized finalized;
    int[] balancedData;
    int loadedTask;


    public ConcurVector(int dim, int threads){
        super(dim);
        this.dimension = dim;
        this.threads = threads;
        this.balance(dim, threads);
        buf = new Bafer(loadedTask);
        finalized = new TasksFinalized(loadedTask);
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
        for (int load: arrayThreads) {
            if (load > 0)
                loadedTask++;
        }
        this.setBalancedData(arrayThreads);
    }

    public SeqVector splitElements(int start, int end){
        return new SeqVector(Arrays.copyOfRange(elements, start, end));
    }

    /** Pone el valor d en todas las posiciones del vector.
     * @param d, el valor a ser asignado. */
    public void set(double d) {
        for (int i = 0; i < balancedData.length; i++){
            if (balancedData[i] > 0){
                Task t = new Task(Operation.SET, splitElements(i, balancedData[i]), i);
                t.setValue(d);
                buf.queue(t);
            }
        }
        finalized.allTaskCompleted();
        makeResultVector();
    }

    private void makeResultVector() {
        for (Task ts: finalized.getFinalized()) {
            for (int ti = ts.getPosition(); ti < ts.getOriginalVector().dimension()+ts.getPosition(); ti++) {
                elements[ti+ts.getPosition()] = ts.getOriginalVector().get(ti);
            }
            //ts.getOriginalVector()
//            for (int i = 0; i < elements.length ; i++) {
  //              this.elements[i] = ts.getOriginalVector().get(i-ts.getPosition());
    //        }
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
