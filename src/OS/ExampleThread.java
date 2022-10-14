package OS;

public class ExampleThread {

    public static void main(String[] args) throws InterruptedException {
        ThreadClassLetters letters = new ThreadClassLetters();
        ThreadClassNumbers numbers = new ThreadClassNumbers();
        numbers.start();
        numbers.join();
        letters.start();
        letters.join();

    }

}

class ThreadClassNumbers extends Thread {
    static int count = 0;

    @Override
    public void run() {
        for(int i = 0; i<5;i++) {
            System.out.print(count+" ");
            count++;
        }

    }
}


class ThreadClassLetters extends Thread {
    ThreadClassNumbers num = new ThreadClassNumbers();
    @Override
    public void run() {
        for(int i = 0; i<5;i++) {
            System.out.print((char)(num.count + 65) + " ");
            num.count++;
        }

    }
}
