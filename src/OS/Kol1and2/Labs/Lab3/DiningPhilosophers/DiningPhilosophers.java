package OS.Kol1and2.Labs.Lab3.DiningPhilosophers;

import java.util.Random;
import java.util.concurrent.Semaphore;

class Demo {

    public static void main(String[] args) throws InterruptedException {
        DiningPhilosophers.runTest();
    }
}

class DiningPhilosophers {

    private static final Random random = new Random(System.currentTimeMillis());
    private final Semaphore[] forks = new Semaphore[6];

    public DiningPhilosophers() {
        forks[0] = new Semaphore(1);
        forks[1] = new Semaphore(1);
        forks[2] = new Semaphore(1);
        forks[3] = new Semaphore(1);
        forks[4] = new Semaphore(1);
        forks[5] = new Semaphore(1);
    }

    public void lifecycleOfPhilosopher(int id) throws InterruptedException {

        while (true) {
            think(id);
            eat(id);
        }
    }

    void think(int id) throws InterruptedException {
        Thread.sleep(random.nextInt(50));
        System.out.printf("Philosopher %d is thinking...\n", id);
    }

    void eat(int id) throws InterruptedException {
        // TODO: synchronize

        forks[id].acquire();
        if (forks[((id + 1) % 6)].tryAcquire()) {
            System.out.printf("Philosopher %d is eating...\n", id);
            forks[((id + 1) % 6)].release();
        }
        forks[id].release();

    }

    static void runPhilosopher(DiningPhilosophers dp, int id) {
        try {
            dp.lifecycleOfPhilosopher(id);
        } catch (InterruptedException ie) {

        }
    }

    public static void runTest() throws InterruptedException {
        final DiningPhilosophers dp = new DiningPhilosophers();

        Thread p1 = new Thread(new Runnable() {

            public void run() {
                runPhilosopher(dp, 0);
            }
        });

        Thread p2 = new Thread(new Runnable() {

            public void run() {
                runPhilosopher(dp, 1);
            }
        });

        Thread p3 = new Thread(new Runnable() {

            public void run() {
                runPhilosopher(dp, 2);
            }
        });

        Thread p4 = new Thread(new Runnable() {

            public void run() {
                runPhilosopher(dp, 3);
            }
        });

        Thread p5 = new Thread(new Runnable() {

            public void run() {
                runPhilosopher(dp, 4);
            }
        });

        Thread p6 = new Thread(new Runnable() {

            public void run() {
                runPhilosopher(dp, 5);
            }
        });

        p1.start();
        p2.start();
        p3.start();
        p4.start();
        p5.start();
        p6.start();

        p1.join();
        p2.join();
        p3.join();
        p4.join();
        p5.join();
        p6.join();

    }
}