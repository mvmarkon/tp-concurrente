package model;

import model.Task.Operation;
import java.util.Arrays;

public class ConcurVector extends SeqVector{

    //private double[] elements;
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

    public Bafer getBuf() {
        return buf;
    }

    public TasksFinalized getFinalized() {
        return finalized;
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
    @Override
    /** Pone el valor d en todas las posiciones del vector.
     * @param d, el valor a ser asignado. */
    public void set(double d) {
        System.out.println("SET");
        int offset = 0;
        for (int i = 0; i < balancedData.length; i++){
            if (balancedData[i] > 0){
                Task t = new Task(Operation.SET, splitElements(offset, offset + balancedData[i]), i);
                t.setValue(d);
                buf.queue(t);
            }
            offset += balancedData[i];
        }
        finalized.allTaskCompleted();
        makeResultVector();
    }

    private void makeResultVector() {
        int offset = 0;
        for (Task ts: finalized.getFinalized()) {
            for (int ti = 0; ti < ts.getOriginalVector().dimension(); ti++) {
                //System.out.println("Tarea  nro: " + ts.getPosition());
                elements[ti + offset] = ts.getOriginalVector().get(ti);
                //System.out.println("ti+offset : " + ti + " + " + offset);
                //System.out.println("ts.getOriginalVector().get(ti)" + ts.getOriginalVector().get(ti));

            }
            offset += ts.getOriginalVector().dimension();
        }
    }
    public void verlindo() {
        int cont = 0;
        System.out.print("[ ");
        for (double n: elements ) {

            System.out.print(n + ", ");
            cont++;
        }
        System.out.print("]");
    }

    /*private void processTask(Task ts) {
        for (int ti = ts.getPosition(); ti < ts.getOriginalVector().dimension()+ts.getPosition(); ti++) {
            elements[ti+ts.getPosition()] = ts.getOriginalVector().get(ti);
        }
    }*/
    private void processTask(Task ts, int offset) {
        for (int ti = 0; ti < ts.getOriginalVector().dimension(); ti++) {
            elements[ti+offset] = ts.getOriginalVector().get(ti);
        }
    }

    /** Suma los valores de este vector con los de otro (uno a uno).
     * @param v, el vector con los valores a sumar.
     * @precondition dimension() == v.dimension(). */
    @Override
    public void add(SeqVector v) {
        System.out.println("ADD");
        int offset = 0;
        for (int i = 0; i < balancedData.length; i++){
            if (balancedData[i] > 0){
                Task t = new Task(Operation.ADD, splitElements(offset, offset + balancedData[i]), i);
                t.setOtherVector(new SeqVector(Arrays.copyOfRange(v.elements, offset, offset + balancedData[i])));
                buf.queue(t);
            }
            offset += balancedData[i];
        }
        finalized.allTaskCompleted();
        makeResultVector();
    }
}
