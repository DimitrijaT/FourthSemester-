package OS.Kol1and2.Exams.Carnival;


import OS.Kol1and2.Exams.ProblemExecution;
import OS.Kol1and2.Exams.TemplateThread;

import java.util.HashSet;
import java.util.concurrent.Semaphore;

public class CarnivalSolution {


    public static final int GROUP_SIZE = 10;
    public static final int TOTAL = 30;

    private static Semaphore seats;
    private static Semaphore canPresent;
    private static Semaphore newCycle;
    private static Semaphore lock;

    private static int groupNo = 0;
    private static int totalNo = 0;

    public static void init() {
        seats = new Semaphore(10);
        canPresent = new Semaphore(0);
        newCycle = new Semaphore(0);
        lock = new Semaphore(1);
    }

    public static class Participant extends TemplateThread {

        public Participant(int numRuns) {
            super(numRuns);
        }

        @Override
        public void execute() throws InterruptedException {
            seats.acquire();
            state.participantEnter();
            lock.acquire();
            groupNo++;
            if (groupNo == 10) {
                //lock.release();
                canPresent.release(10);
            }
            lock.release();
            canPresent.acquire();
            state.present();

            lock.release();
            groupNo--;
            if (groupNo == 0) {
                state.endGroup();
                seats.release(10);
            }

            lock.acquire();
            if (totalNo == TOTAL) {
                state.endCycle();
                newCycle.release(TOTAL);
                totalNo = 0;
            }
            totalNo++;
            newCycle.acquire();
        }

    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            run();
        }
    }

    static CarnivalState state = new CarnivalState();

    public static void run() {
        try {
            int numRuns = 15;
            int numThreads = 30;

            HashSet<Thread> threads = new HashSet<Thread>();

            for (int i = 0; i < numThreads; i++) {
                Participant c = new Participant(numRuns);
                threads.add(c);
            }

            init();

            ProblemExecution.start(threads, state);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}