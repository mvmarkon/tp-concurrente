package model;

public class ConcurVector {

    private double[] elements;
    int dimension;
    int threads;
    // model.Bafer buf;
    int[] balancedData;


    public ConcurVector(int dim, int threads){
        this.dimension = dim;
        this.threads = threads;
        this.elements = new double[dim];
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

        int n = 0;
        for (int c: arrayThreads) {
            System.out.println(n + " la carga es de "+ c);
            ++n;
        }
        this.setBalancedData(arrayThreads);
    }



    public void balanceElements() {
        int carga = (int)(dimension / threads);
        int [] arrayThreads = new int[threads];
        for (int i = 0; i < arrayThreads.length; i++) {
            arrayThreads[i]= carga;
        }

        int resto = dimension % threads;
        System.out.println("Carga: " + carga + " , resto: "+ resto);
        for(int i = 0; i < resto; i++) {
            arrayThreads[i] = ++arrayThreads[i];

        }
        System.out.println("Threads: " + arrayThreads );

        int n = 0;
        for (int c: arrayThreads) {
            System.out.println(n + " la carga es de "+ c);
            ++n;
        }
    }

    /*public double[][] dividirVector(){
        double[][] elembal = new double[];
        if(dimension>=threads){
            int longitud = dimension/threads;
            int restantes = dimension;
            for(int i = 0;i<threads;i++){
                elembal[i] = double[];
                if(i==threads-1){
                    for (int j=0;j<restantes;j++){
                        elembal[i] = elements[(i*longitud+j)];
                    }
                }
                else{
                    for (int j=0;j<longitud;j++){
                        enteros[i].add(v.get(i*longitud+j));
                    }
                }
                restantes = restantes - longitud;
            }

            return enteros;
        }
        else{
            enteros[0]=(Vector<Integer>)v;
            return enteros;
        }
    }*/


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
     * @param d, el valor a ser asignado en la posicion i.
     * @precondition 0 <= i < dimension. */
    public void set(int i, double d) {
        elements[i] = d;
    }

    /** Suma los valores de este vector con los de otro (uno a uno).
     * @param v, el vector con los valores a sumar.
     * @precondition dimension() == v.dimension(). */
    public void add(SeqVector v) {
        for (int i = 0; i < dimension(); ++i)
            set(i, get(i) + v.get(i));
    }
}
