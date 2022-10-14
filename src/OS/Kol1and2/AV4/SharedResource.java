package OS.Kol1and2.AV4;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SharedResource {

    private static Lock mutex = new ReentrantLock();
    private static Semaphore semaphore = new Semaphore(1);
    private static int counter = 0;

    public int getCounter() {
        return counter;
    }

    public void increment() throws InterruptedException { //synchronized
        semaphore.acquire();
//        mutex.lock();
        //Thread.sleep(1);
//        synchronized (SharedResource.class){
            counter++;
//        }
        //Thread.sleep(1);
//        mutex.unlock();
        semaphore.release();
    }
}
