package OS.Kol1and2.AV3;

public class LazyThread extends Thread {

    long timeToSleep = 0;

    public LazyThread(long timeToSleep) {
        this.timeToSleep = timeToSleep;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(timeToSleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
