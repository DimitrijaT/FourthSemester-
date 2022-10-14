package OS.Kol1and2.Labs.Lab3.Vinegar;

import java.util.HashSet;
import java.util.concurrent.Semaphore;

public class Vinegar {

    public static Semaphore h;
    public static Semaphore c;
    public static Semaphore o;
    public static Semaphore hHere;
    public static Semaphore cHere;
    public static Semaphore oHere;
    public static Semaphore ready;
    public static Semaphore bondingDone;
    public static Semaphore canLeave;
    public static Semaphore lock;

    public static int numH;

    public static void init() {

        h = new Semaphore(4);
        c = new Semaphore(2);
        o = new Semaphore(2);
        hHere = new Semaphore(0);
        cHere = new Semaphore(0);
        oHere = new Semaphore(0);
        ready = new Semaphore(0);
        bondingDone = new Semaphore(0);
        canLeave = new Semaphore(0);
        lock = new Semaphore(1);

        numH = 0;

    }


    public static void main(String[] args) throws InterruptedException {
        HashSet<Thread> threads = new HashSet<>();
        for (int i = 0; i < 20; i++) {
            threads.add(new C());
            threads.add(new H());
            threads.add(new H());
            threads.add(new O());
        }
        init();
        // run all threads in background
        for (Thread t : threads) {
            t.start();
        }
        // after all of them are started, wait each of them to finish for maximum 2_000 ms
        for (Thread t : threads) {
            t.join(2000);
        }
        // for each thread, terminate it if it is not finished
        for (Thread t : threads) {
            if (t.isAlive()) {
                t.interrupt();
                System.out.println("Possible deadlock!");
            }
        }
        System.out.println("Process finished.");

    }

    static class C extends Thread {

        public void execute() throws InterruptedException {
            c.acquire();
            System.out.println("C here.");
            cHere.release();
            ready.acquire();
            System.out.println("Molecule bonding.");
            Thread.sleep(100);
            System.out.println("C done.");
            bondingDone.release();
            canLeave.acquire();
            c.release();
        }

        @Override
        public void run() {
            try {
                execute();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class H extends Thread {

        public void execute() throws InterruptedException {
            // at most 4 atoms should print this in parallel
            h.acquire();
            System.out.println("H here.");
            lock.acquire();
            numH++;
            if (numH == 4) {
                numH = 0;
                lock.release();
                hHere.acquire(3);
                cHere.acquire(2);
                oHere.acquire(2);
                ready.release(7);
                System.out.println("Molecule bonding.");
                Thread.sleep(100);
                System.out.println("H done.");
                bondingDone.acquire(7);
                System.out.println("Molecule created.");
                canLeave.release(7);
            } else {
                lock.release();
                hHere.release();
                ready.acquire();
                System.out.println("Molecule bonding.");
                Thread.sleep(100);
                System.out.println("H done.");
                bondingDone.release();
                canLeave.acquire();
            }
            h.release();
        }

        @Override
        public void run() {
            try {
                execute();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class O extends Thread {

        public void execute() throws InterruptedException {
            o.acquire();
            System.out.println("O here.");
            oHere.release();
            ready.acquire();
            System.out.println("Molecule bonding.");
            Thread.sleep(100);
            System.out.println("O done.");
            bondingDone.release();
            canLeave.acquire();
            o.release();
        }

        @Override
        public void run() {
            try {
                execute();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

