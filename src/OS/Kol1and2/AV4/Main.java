package OS.Kol1and2.AV4;

public class Main {
    public static void main(String[] args) throws InterruptedException {
//        SharedResource resource = new SharedResource();
//
//        IncrementThread t1 = new IncrementThread(resource);
//        IncrementThread t2 = new IncrementThread(resource);
//        IncrementThread t3 = new IncrementThread(resource);
//        IncrementThread t4 = new IncrementThread(resource);
//
//        t1.start();
//        t2.start();
//        t3.start();
//        t4.start();
//
//
//        t1.join();
//        t2.join();
//        t3.join();
//        t4.join();
//
//        System.out.println(resource.getCounter());


        SharedResource resource1 = new SharedResource();
        SharedResource resource2 = new SharedResource();
        SharedResource resource3 = new SharedResource();
        SharedResource resource4 = new SharedResource();

        IncrementThread t1 = new IncrementThread(resource1);
        IncrementThread t2 = new IncrementThread(resource2);
        IncrementThread t3 = new IncrementThread(resource3);
        IncrementThread t4 = new IncrementThread(resource4);

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();

        System.out.println(resource1.getCounter());
        System.out.println(resource2.getCounter());
        System.out.println(resource3.getCounter());
        System.out.println(resource4.getCounter());
    }
}
