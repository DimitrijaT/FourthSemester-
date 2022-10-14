package OS.Kol1and2.Labs.Lab3.BasketballTournament;

import java.util.HashSet;
import java.util.concurrent.Semaphore;

public class BasketballTournament {

    public static void main(String[] args) throws InterruptedException {
        HashSet<Player> threads = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            Player p = new Player();
            threads.add(p);
        }

        Player.init();
        // run all threads in background
        for (Player t : threads) {
            t.start();
        }
        // after all of them are started, wait each of them to finish for maximum 5_000 ms
        for (Player t : threads) {
            t.join(5000);
        }
        // for each thread, terminate it if it is not finished
        for (Player t : threads) {
            if (t.isAlive()) {
                t.interrupt();
                System.out.println("Possible deadlock!");
            }
        }

        System.out.println("Tournament finished.");

    }
}

class Player extends Thread {

    static Semaphore voSala;
    static Semaphore salaHere;
    static Semaphore sReady;

    static Semaphore voKabina;
    static Semaphore kabinaHere;
    static Semaphore kReady;

    static Semaphore voTour;
    static Semaphore tourHere;
    static Semaphore tReady;
    static Semaphore tourFinished;

    static int numSala;
    static int numKabina;
    static int numTour;

    static Semaphore lock;

    static void init() {
        voSala = new Semaphore(20);
        voKabina = new Semaphore(10);
        voTour = new Semaphore(20);

        salaHere = new Semaphore(0);
        kabinaHere = new Semaphore(0);
        tourHere = new Semaphore(0);

        sReady = new Semaphore(0);
        kReady = new Semaphore(0);
        tReady = new Semaphore(0);

        tourFinished = new Semaphore(0);

        lock = new Semaphore(1);
        numSala = 0;
        numKabina = 0;
        numTour = 0;
    }


    public void execute() throws InterruptedException {
        // at most 20 players should print this in parallel
        voSala.acquire();
        lock.acquire();
        numSala++;
        if (numSala == 20) {
            numSala = 0;
            lock.release();
            salaHere.acquire(19);
            sReady.release(19);
            System.out.println("Player inside.");
        } else {
            lock.release();
            salaHere.release();
            sReady.acquire();
            System.out.println("Player inside.");
        }

        // at most 10 players may enter in the dressing room in parallel
        voKabina.acquire();
        lock.acquire();
        numKabina++;
        if (numKabina == 10) {
            numKabina = 0;
            lock.release();
            kabinaHere.acquire(9);
            kReady.release(9);
            System.out.println("In dressing room.");// this represent the dressing time
            Thread.sleep(10);
        } else {
            lock.release();
            kabinaHere.release();
            kReady.acquire();
            System.out.println("In dressing room.");// this represent the dressing time
            Thread.sleep(10);
        }
        voKabina.release();

        // after all players are ready, they should start with the game together


        voTour.acquire();
        lock.acquire();
        numTour++;
        if (numTour == 20) {
            numTour = 0;
            lock.release();
            tourHere.acquire(19);
            tReady.release(19);
            System.out.println("Game started.");
            Thread.sleep(100);// this represent the game duration
            System.out.println("Player done.");
            tourFinished.acquire(19);
            System.out.println("Game finished.");   // only one player should print the next line, representing that the game has finished
        } else {
            lock.release();
            tourHere.release();
            tReady.acquire();
            System.out.println("Game started.");
            Thread.sleep(100);// this represent the game duration
            System.out.println("Player done.");
            tourFinished.release();
        }
        voTour.release();
        voSala.release();
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