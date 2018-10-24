import model.Bafer;
import model.ConcurVector;
import model.SeqVector;

public class Main {

    public static void main(String[] args) {
        Bafer cola = new Bafer(5);

        System.out.println("Se creo una colasincornizazda de " + cola.getSize() + " espacios");

        /*
        cola.queue(1.0);
        cola.queue(2.0);
        cola.queue(3.0);
        cola.queue(4.0);
        cola.queue(5.0);
*/
        System.out.println("La cola deberia estar llena");
        System.out.println("Se empieza a desencolar: ");

        System.out.println("primero: " + cola.dequeue());
        System.out.println("segundo: " + cola.dequeue());
        System.out.println("tercero: " + cola.dequeue());
        System.out.println("cuarto: " + cola.dequeue());
        System.out.println("quinto: " + cola.dequeue());

        ConcurVector cv = new ConcurVector(11, 6);
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

        //cv.balance();

        cv.add(seqVector);
        System.out.println("sumo...");
        for(int i = 0; i < cv.dimension(); i++){
            System.out.println(cv.get(i));
        }

    }
}
