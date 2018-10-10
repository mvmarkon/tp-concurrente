public class Worker {

    class SecuenciadorTernario {
        public int next = 1;

        private boolean puedePrimero() {
            return next == 1;
        }

        private boolean puedeSegundo() {
            return next == 2;
        }

        private boolean puedeTercero() {
            return next == 3;
        }

        private void seEjecutoPrimero() {
            next = 2;
            notifyAll();
        }

        private void seEjecutoSegundo() {
            next = 3;
            notifyAll();
        }

        private void seEjecutoTercero() {
            next = 1;
            notifyAll();
        }

        synchronized void primero() {
            while (!puedePrimero()) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    return;
                }
            }
            seEjecutoPrimero();
            System.out.println("Se ejecuto la primer operacion ");
        }

        public synchronized void segundo() {
            while (!puedeSegundo()) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.getMessage();
                }
            }
            seEjecutoSegundo();
            System.out.println("Se ejecuto la segunda operacion ");
        }

        public synchronized void tercero() {
            while (!puedeTercero()) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.getMessage();
                }
            }
            seEjecutoTercero();
            System.out.println("Se ejecuto la tercera operacion ");
        }
    }


    class T1 implements Runnable {
        private final SecuenciadorTernario s;

        public T1(SecuenciadorTernario s) {
            this.s = s;
        }

        public void run() {
            while (true) {
                s.primero();
            }
        }
    }

    class T3 implements Runnable {
        private final SecuenciadorTernario s;

        public T3(SecuenciadorTernario s) {
            this.s = s;
        }

        public void run() {
            while (true) {
                s.tercero();
            }
        }
    }

    class T2 implements Runnable {
        private final SecuenciadorTernario s;

        public T2(SecuenciadorTernario s) {
            this.s = s;
        }

        public void run() {
            while (true) {
                s.segundo();
            }
        }
    }

/*    public static void main(String[] args) {
        SecuenciadorTernario sec = new SecuenciadorTernario();

        T1 uno = new T1(sec);
        T2 dos = new T2(sec);
        T3 tres = new T3(sec);

        uno.start();
        dos.start();
        tres.start();

    }*/
}