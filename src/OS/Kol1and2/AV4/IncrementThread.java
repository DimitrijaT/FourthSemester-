package OS.Kol1and2.AV4;

public class IncrementThread extends Thread{

    private SharedResource resource;

    public IncrementThread(SharedResource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        for (int i=0;i<5000;i++){
            try {
                resource.increment();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
