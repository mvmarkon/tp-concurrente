public class Main {

    public static void main(String[] args) {
        Bafer cola = new Bafer(5);

        System.out.println("Se creo una colasincornizazda de " + cola.getSize() + " espacios");

        cola.queue(1.0);
        cola.queue(2.0);
        cola.queue(3.0);
        cola.queue(4.0);
        cola.queue(5.0);

        System.out.println("La cola deberia estar llena");
        System.out.println("Se empieza a desencolar: ");

        System.out.println("primero: " + cola.dequeue());
        System.out.println("segundo: " + cola.dequeue());
        System.out.println("tercero: " + cola.dequeue());
        System.out.println("cuarto: " + cola.dequeue());
        System.out.println("quinto: " + cola.dequeue());

    }
}
