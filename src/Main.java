import model.ConcurVector;
import model.ThreadPool;

public class Main {

    public static void main(String[] args) {
        /*Bafer cola = new Bafer(5);

        System.out.println("Se creo una colasincornizazda de " + cola.getSize() + " espacios");
        Task t1 = new Task();
        Task t2 = new Task();
        Task t3 = new Task();
        Task t4 = new Task();
        Task t5 = new Task();

        System.out.println("La cola NO deberia estar llena");
        System.out.println(cola.isFull());

        cola.queue(t1);
        cola.queue(t2);
        cola.queue(t3);
        cola.queue(t4);
        cola.queue(t5);

        System.out.println("La cola deberia estar llena");
        System.out.println(cola.isFull());
        System.out.println("Se empieza a desencolar: ");

        System.out.println("primero: " + cola.dequeue());
        System.out.println("segundo: " + cola.dequeue());
        System.out.println("tercero: " + cola.dequeue());
        System.out.println("cuarto: " + cola.dequeue());
        System.out.println("quinto: " + cola.dequeue());

        System.out.println("La cola deberia estar vacia");
        System.out.println(cola.isEmpty());
        System.out.println("La cola NO deberia estar llena");
        System.out.println(cola.isFull());*/
        /*ConcurVector cv = new ConcurVector(11, 6);
        //cv.balanceElements();
        double val = 1;
        for(int i = 0; i < cv.dimension(); i++){
            cv.set(i, val);
            System.out.println(cv.get(i));
        }
        System.out.println(cv);

        SeqVector seqVector = new SeqVector(11);
        for(int i = 0; i < seqVector.dimension(); i++){
            seqVector.set(i, i);
            System.out.println(i);
        }
*/
        //cv.balance();

  //      cv.add(seqVector);
    /*    System.out.println("sumo...");
        for(int i = 0; i < cv.dimension(); i++){
            System.out.println(cv.get(i));
        }
*/
        int threads = 2;
        int dimension = 4;
        ThreadPool tp = new ThreadPool(threads);
        ConcurVector cv = new ConcurVector(dimension, threads);

        tp.createWorkers(cv.getBuf(), cv.getFinalized());

        // prueba para SET
        cv.set(2);
        cv.verlindo();

        //SeqVector sv = new SeqVector(11);
        //sv.set(2);

        // prueba para ADD
        //cv.add(sv);

        // prueba para ASSIGN
        //cv.assign(sv);

        // prueba para MUL
        //cv.mul(sv);

        //Prueba de ABS

        //cv.abs();

        //PRUEBA DE SUM
        System.out.println("SUMAAAAAAAA" + cv.sum());
/*
        //  prueba para ASSIGN con MASK
        SeqVector mask = new SeqVector(11);
        //mask.set(0);
        mask.set(-1);
        mask.set(0, 7);
        mask.set(1, 7);
        cv.assign(mask, sv);
*/


        // SIEMPRE SE MUESTRA AL FINAL CONCUR VECTOR
        cv.verlindo();

    }
}
