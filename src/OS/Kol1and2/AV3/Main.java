package OS.Kol1and2.AV3;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int numThreads = 10;


        List<LazyThread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new LazyThread(1000)); //<- BORN
        }

        long startTime = System.currentTimeMillis();
        for (LazyThread thread : threads) {
            thread.start(); //For multiple threads Pocni so izvrshuvanje! 0 sekundi
            //thread.run(); // Ne! Ova e se na isti thread. 10 sekundi.
        }
        long endTime = System.currentTimeMillis();

        System.out.println(endTime - startTime);

        for (LazyThread thread : threads) {
            thread.join(); //Ako sakame da gi ischekame site threadovi da zavrshat so rabota ! (1 sekunda)
        }
        endTime = System.currentTimeMillis();

        System.out.println(endTime - startTime);
    }
}
