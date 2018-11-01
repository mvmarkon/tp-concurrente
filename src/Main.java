import model.ConcurVector;

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
        int threads = 1;
        double[] el = {3,4,500,100};
        int dimension = 4;

        ConcurVector cv = new ConcurVector(dimension, threads);
        //cv.setElements(el);
       ConcurVector cv2 = new ConcurVector(dimension, threads);
       cv.setElements(el);
       // prueba para SET
        //cv.set(1);
        //cv2.set(3);
       // cv.setElements(el);
        //cv.verlindo();
        //cv.add(cv2);
        //SeqVector sv = new SeqVector(2);
        //sv.set(2);

        // prueba para ADD

        //cv.add();

        // prueba para ASSIGN
        //cv.assign(cv2);

        // prueba para MUL
        //cv.mul(cv2);

        //Prueba de ABS

        //cv.abs();

        //PRUEBA DE SUM
        //System.out.println("SUMAAAAAAAA " + cv.sum());
        //System.out.println("MEAN/PROMEDIO " + cv.mean());
        //System.out.println("PROD " + cv.prod(cv2));
        //System.out.println("NORM " + cv.norm());
        System.out.println("MAX " + cv.max());


        //  prueba para ASSIGN con MASK
        /*ConcurVector mask = new ConcurVector(dimension, threads);
        mask.set(0);
        //mask.set(-1);
        mask.set(0, 7);
        mask.set(1, 7);
        cv.assign(mask, cv2);

        mask.verlindo();*/

        // SIEMPRE SE MUESTRA AL FINAL CONCUR VECTOR
        //cv.verlindo();
        //cv2.verlindo();

    }
}
