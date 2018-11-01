package model;

import model.Task.Operation;
import java.util.Arrays;

public class ConcurVector {

    private double[] elements;
    int dimension;
    int threads;
    Bafer buf;
    TasksFinalized finalized;
    int[] balancedData;
    int loadedTask;
    ThreadPool tp;
    Barrier barrier;


    public ConcurVector(int dim, int threads){
        this.elements = new double[dim];
        this.dimension = dim;
        this.threads = threads;
        this.balancedData = this.balance(dim, threads);
        this.buf = new Bafer(loadedTask);
        this.finalized = new TasksFinalized();
        this.barrier = new Barrier();
        this.tp = new ThreadPool(threads);
        tp.createWorkers(this.buf, this.finalized, this.barrier);
    }

    public synchronized ConcurVector seqToConcurVector(double[] sv, int threads) {
        ConcurVector sec2conc = new ConcurVector(sv.length, threads);
        sec2conc.elements = sv;
        return sec2conc;
    }

    public synchronized void setElements(double[] els) {
        this.elements = els;
    }

    public synchronized Bafer getBuf() {
        return buf;
    }

    public synchronized TasksFinalized getFinalized() {
        return finalized;
    }

    public synchronized void setBalancedData(int[] balance){
        this.balancedData = balance;
    }


    private synchronized int[] balance(int dimension, int threads) {
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
        return arrayThreads;
    }


    public synchronized SeqVector splitElements(int start, int end){
        return new SeqVector(Arrays.copyOfRange(elements, start, end));
    }


    private synchronized void makeResultVector() {
        int offset = 0;
        for (Task ts: finalized.getFinalized()) {
            for (int ti = 0; ti < ts.getOriginalVector().dimension(); ti++) {
                elements[ti + offset] = ts.getOriginalVector().get(ti);

            }
            offset += ts.getOriginalVector().dimension();
        }
    }
    private synchronized double[] makeResult() {
        double[] resultElements = new double[finalized.getFinalized().length];
        for (Task ts: finalized.getFinalized()) {
            resultElements[ts.getPosition()] = ts.getResult();
        }
        return resultElements;
    }


    public synchronized void verlindo() {
        System.out.print("[ ");
        for (double n: elements ) {

            System.out.print(n + ", ");
        }
        System.out.print("]");
    }



    /**
     *  REIMPLEMENTACION DE LOS METODOS DE SEQ_VECTOR
     */



    /** Pone el valor d en todas las posiciones del vector.
     * @param d, el valor a ser asignado. */

    public synchronized void set(double d) {
        System.out.println("SET");
        this.organizeTasks(d, Operation.SET);
        //this.barrier.allTaskscreated();
        this.barrier.canProceed();
        this.makeResultVector();
    }



    /** Suma los valores de este vector con los de otro (uno a uno).
     * @param v, el vector con los valores a sumar.
     * @precondition dimension() == v.dimension(). */
    public synchronized void add(ConcurVector v) {
        System.out.println("ADD");
        this.organizeTasks(v, Operation.ADD);
        this.barrier.canProceed();
        this.makeResultVector();
    }





    /** Copia los valores de otro vector sobre este vector.
     * @param v, el vector del que se tomaran los valores nuevos.
     * @precondition dimension() == v.dimension(). */
    public synchronized void assign(ConcurVector v) {
        System.out.println("ASSIGN simple");
        this.organizeTasks(v, Operation.ASSIGN);
        this.barrier.canProceed();
        this.makeResultVector();
    }





    /** Copia algunos valores de otro vector sobre este vector.
     * Un vector mascara indica cuales valores deben copiarse.
     * @param mask, vector que determina si una posicion se debe copiar.
     * @param v, el vector del que se tomaran los valores nuevos.
     * @precondition dimension() == mask.dimension() && dimension() == v.dimension(). */
    public synchronized void assign(ConcurVector mask, ConcurVector v) {
        System.out.println("ASSIGN with mask");
        this.organizeTasks(mask, v, Operation.ASSIGN_MASK);
        this.barrier.canProceed();
        this.makeResultVector();
    }



    /** Multiplica los valores de este vector con los de otro
     *  (uno a uno).
     * @param v, el vector con los valores a multiplicar.
     * @precondition dimension() == v.dimension(). */
    public synchronized void mul(ConcurVector v) {
        this.organizeTasks(v, Operation.MUL);
        this.barrier.canProceed();
        this.makeResultVector();
    }



    /** Obtiene el valor absoluto de cada elemento del vector. */
    public synchronized void abs() {
        this.organizeTasks(Operation.ABS);
        this.barrier.canProceed();
        this.makeResultVector();
    }

    /** Obtiene la suma de todos los valores del vector. */
    public synchronized double sum() {
        this.organizeTasks(Operation.SUM);
        this.barrier.canProceed();
        double[] result = this.makeResult();
        while(result.length > threads){
            ConcurVector smallerCV = seqToConcurVector(result, threads);
            smallerCV.sum();
            result = smallerCV.elements;
        }
        SeqVector rs = new SeqVector(result);
        return rs.sum();
    }

    /** Obtiene el valor promedio en el vector. */
    public synchronized double mean() {
        double total = sum();
        return total / dimension;
    }

    /** Retorna el producto de este vector con otro.
     * El producto vectorial consiste en la suma de los productos
     * de cada coordenada.
     * @param v, el vector a usar para realizar el producto.
     * @precondition dimension() == v.dimension(). */
    public synchronized double prod(ConcurVector v) {
        ConcurVector aux = new ConcurVector(dimension, threads);
        aux.assign(this);
        aux.mul(v);
        return aux.sum();
    }


    /** Retorna la norma del vector.
     *  Recordar que la norma se calcula haciendo la raiz cuadrada de la
     *  suma de los cuadrados de sus coordenadas.
     */
    public synchronized double norm() {
        ConcurVector aux = new ConcurVector(dimension, threads);
        aux.assign(this);
        aux.mul(this);
        return Math.sqrt(aux.sum());
    }

    /** Obtiene el valor maximo en el vector. */
    public synchronized double max() {
        this.organizeTasks(Operation.MAX);
        this.barrier.canProceed();
        double[] result = this.makeResult();
        System.out.println(result);
        while(result.length > threads){
            ConcurVector smallerCV = seqToConcurVector(result, threads);
            smallerCV.max();
            result = smallerCV.elements;
            System.out.println(result);
        }
        SeqVector rs = new SeqVector(result);
        return rs.max();
    }


    //   organizadores de tareas


    private synchronized void organizeTasks(double d, Operation operation){
        this.finalized.setTasks(this.loadedTask);
        this.barrier.setWorkers(this.loadedTask);
        int offset = 0;
        for (int i = 0; i < balancedData.length; i++){
            if (balancedData[i] > 0){
                Task t = new Task(operation, splitElements(offset, offset + balancedData[i]), i);
                t.setValue(d);
                buf.queue(t);
            }
            offset += balancedData[i];
        }
        System.out.println("Se crearon y  agregaron las tareas");
    }



    private synchronized void organizeTasks(Operation operation){
        this.finalized.setTasks(this.loadedTask);
        this.barrier.setWorkers(this.loadedTask);
        int offset = 0;
        for (int i = 0; i < this.balancedData.length; i++){
            if (this.balancedData[i] > 0){
                Task t = new Task(operation, splitElements(offset, offset + this.balancedData[i]), i);
                this.buf.queue(t);
            }
            offset += this.balancedData[i];
        }
        System.out.println("Se crearon y  agregaron las tareas");
    }


    private synchronized void organizeTasks(ConcurVector v, Operation operation){
        this.finalized.setTasks(this.loadedTask);
        this.barrier.setWorkers(this.loadedTask);
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


    private synchronized void organizeTasks(ConcurVector mask, ConcurVector v, Operation operation){
        this.finalized.setTasks(this.loadedTask);
        this.barrier.setWorkers(this.loadedTask);
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


    /** Retorna la longitud del vector; es decir, su dimension. */
    public int dimension() {
        return elements.length;
    }


    /** Retorna el elemento del vector en la posicion i.
     * @param i, la posicion del elemento a ser retornado.
     * @precondition 0 <= i < dimension(). */
    public double get(int i) {
        return elements[i];
    }


    /** Pone el valor d en la posicion i del vector.
     * @param i, la posicion a setear.
     * @PARAM D, EL VALOR A SER ASIGNADO EN LA POSICION I.
     * @precondition 0 <= i < dimension. */
    public void set(int i, double d) {
        elements[i] = d;
    }


}
