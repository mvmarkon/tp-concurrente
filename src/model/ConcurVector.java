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
        this.balancedData = this.balance(dim, threads);
        this.buf = new Bafer(loadedTask);
        this.finalized = new TasksFinalized(loadedTask);
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


    public int[] balance(int dimension, int threads) {
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
        //this.setBalancedData(arrayThreads);
        return arrayThreads;
    }


    public SeqVector splitElements(int start, int end){
        return new SeqVector(Arrays.copyOfRange(elements, start, end));
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


    private void processTask(Task ts, int offset) {
        for (int ti = 0; ti < ts.getOriginalVector().dimension(); ti++) {
            this.elements[ti+offset] = ts.getOriginalVector().get(ti);
        }
    }






    /**
     *  REIMPLEMENTACION DE LOS METODOS DE SEQ_VECTOR
     */



    /** Pone el valor d en todas las posiciones del vector.
     * @param d, el valor a ser asignado. */
    @Override
    public void set(double d) {
        System.out.println("SET");
        this.organizeTasks(d, Operation.SET);
        this.finalized.allTaskCompleted();
        this.makeResultVector();
    }



    /** Suma los valores de este vector con los de otro (uno a uno).
     * @param v, el vector con los valores a sumar.
     * @precondition dimension() == v.dimension(). */
    @Override
    public void add(SeqVector v) {
        System.out.println("ADD");
        this.organizeTasks(v, Operation.ADD);
        this.finalized.allTaskCompleted();
        this.makeResultVector();
    }





    /** Copia los valores de otro vector sobre este vector.
     * @param v, el vector del que se tomaran los valores nuevos.
     * @precondition dimension() == v.dimension(). */
    @Override
    public void assign(SeqVector v) {
        System.out.println("ASSIGN simple");
        this.organizeTasks(v, Operation.ASSIGN);
        this.finalized.allTaskCompleted();
        this.makeResultVector();
    }





    /** Copia algunos valores de otro vector sobre este vector.
     * Un vector mascara indica cuales valores deben copiarse.
     * @param mask, vector que determina si una posicion se debe copiar.
     * @param v, el vector del que se tomaran los valores nuevos.
     * @precondition dimension() == mask.dimension() && dimension() == v.dimension(). */
    @Override
    public void assign(SeqVector mask, SeqVector v) {
        System.out.println("ASSIGN with mask");
        this.organizeTasks(mask, v, Operation.ASSIGN_MASK);
        this.finalized.allTaskCompleted();
        this.makeResultVector();
    }



    /** Multiplica los valores de este vector con los de otro
     *  (uno a uno).
     * @param v, el vector con los valores a multiplicar.
     * @precondition dimension() == v.dimension(). */
    @Override
    public void mul(SeqVector v) {
        this.organizeTasks(v, Operation.MUL);
        this.finalized.allTaskCompleted();
        this.makeResultVector();
    }



    /** Obtiene el valor absoluto de cada elemento del vector. */
    @Override
    public void abs() {
        this.organizeTasks(Operation.ABS);
        this.finalized.allTaskCompleted();
        this.makeResultVector();
    }





    //   organizadores de tareas


    private void organizeTasks(double d, Operation operation){
        int offset = 0;
        for (int i = 0; i < balancedData.length; i++){
            if (balancedData[i] > 0){
                Task t = new Task(operation, splitElements(offset, offset + balancedData[i]), i);
                t.setValue(d);
                buf.queue(t);
            }
            offset += balancedData[i];
        }

    }



    private void organizeTasks(Operation operation){
        int offset = 0;
        for (int i = 0; i < this.balancedData.length; i++){
            if (this.balancedData[i] > 0){
                Task t = new Task(operation, splitElements(offset, offset + this.balancedData[i]), i);
                this.buf.queue(t);
            }
            offset += this.balancedData[i];
        }
    }


    private void organizeTasks(SeqVector v, Operation operation){
        int offset = 0;
        for (int i = 0; i < this.balancedData.length; i++){
            if (this.balancedData[i] > 0){
                Task t = new Task(operation, splitElements(offset, offset + this.balancedData[i]), i);
                t.setOtherVector(new SeqVector(Arrays.copyOfRange(v.elements, offset, offset + this.balancedData[i])));
                this.buf.queue(t);
            }
            offset += this.balancedData[i];
        }
    }


    private void organizeTasks(SeqVector mask, SeqVector v, Operation operation){
        int offset = 0;
        for (int i = 0; i < balancedData.length; i++){
            if (balancedData[i] > 0){
                Task t = new Task(operation, splitElements(offset, offset + balancedData[i]), i);
                t.setOtherVector(new SeqVector(Arrays.copyOfRange(v.elements, offset, offset + balancedData[i])));
                t.setAuxVector(new SeqVector(Arrays.copyOfRange(mask.elements, offset, offset + balancedData[i])));
                buf.queue(t);
            }
            offset += balancedData[i];
        }

    }





}
