public class ConcurVector {

    private double[] elements;
    int dimension, threads, load;
    // Bafer buf;


    public ConcurVector(int dim, int threads/*, int load*/){
        this.dimension = dim;
        this.threads = threads;
        //this.load = load;
        elements = new double[dim];
    }

    /*private void balanceElements() {
        int carga = (int)(dimension / threads);
        for  {

        }
        for (int i = 0; i < ; i++) {

        }
    }

    public double[][] dividirVector(){
        double[][] elembal = new double[];
        if(v.size()>=n){
            int longitud = v.size()/n;
            int restantes = v.size();
            for(int i = 0;i<n;i++){
                enteros[i] = (Vector<Integer>) new Vector<Integer>();
                if(i==n-1){
                    for (int j=0;j<restantes;j++){
                        enteros[i].add(v.get(i*longitud+j));
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
