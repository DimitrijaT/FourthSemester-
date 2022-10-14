package OS.Kol1and2.Labs.Lab3.Singleton;


import java.util.HashSet;
import java.util.concurrent.Semaphore;

public class Singleton extends Thread {

    private static volatile Singleton singleton;
    public static Semaphore single;

    private Singleton() {

    }

    static void init() {
        single = new Semaphore(1);
    }

    public static Singleton getInstance() throws InterruptedException {
        // TODO: 3/29/20 Synchronize this
        single.acquire();
        singleton = new Singleton();

        return singleton;
    }

    public static void main(String[] args) throws InterruptedException {
        // TODO: 3/29/20 Simulate the scenario when multiple threads call the method getInstance

        HashSet<Singleton> threads = new HashSet<>();

        for (int i = 0; i < 500; i++) {
            threads.add(new Singleton());
        }
        init();
        for (Singleton t : threads) {
            t.start();
        }

        for (Singleton t : threads) {
            t.join(1000);
        }
        for (Singleton t : threads) {
            if (t.isAlive()) {
                t.interrupt();
            }
        }


    }


    @Override
    public void run() {
        try {
            Singleton singleton = getInstance();
            System.out.printf("Threadot %d beshe prv koj napravi instanca od klasata singleton",this.getId());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}