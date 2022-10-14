package OS.Kol1and2.AV5;

import java.util.concurrent.Semaphore;

public class SiO2 {

    public static int NUM_RUN = 50;

    public static Semaphore si;
    public static Semaphore o;
    public static Semaphore siHere;
    public static Semaphore oHere;
    public static Semaphore ready;


    public static void init() {
        si  = new Semaphore(1);
        o = new Semaphore(2);
        siHere = new Semaphore(0);
        oHere = new Semaphore(0);
        ready = new Semaphore(0);
    }

    public static class Si extends Thread {

        public void bond() {
            System.out.println("Si is bonding now.");
        }

        @Override
        public void run() {
            for (int i=0;i<NUM_RUN;i++) {
                try {
                    execute();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void execute() throws InterruptedException {
            si.acquire();
            siHere.release(2);
            oHere.acquire(2);
            ready.release(2);
            bond();
            si.release();
        }

    }

    public static class O extends Thread {

        public void execute() throws InterruptedException {
                o.acquire();
                siHere.acquire();
                oHere.release();
                ready.acquire();
                bond();
                o.release();
        }

        public void bond() {
            System.out.println("O is bonding now.");
        }


        @Override
        public void run() {
            for (int i=0;i<NUM_RUN;i++) {
                try {
                    execute();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
