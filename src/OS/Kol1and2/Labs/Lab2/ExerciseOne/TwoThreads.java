package OS.Kol1and2.Labs.Lab2.ExerciseOne;


import java.util.ArrayList;
import java.util.List;

public class TwoThreads {


    public static void main(String[] args) throws InterruptedException {
        List<Integer> numberList = new ArrayList<>();
        List<Character> charList = new ArrayList<>();

        for (int i = 0; i < 10; i++) numberList.add(i);
        for (int i = 0; i < 10; i++) charList.add((char) (i + 65));

        ThreadClassLettersNumbers<Character> letters = new ThreadClassLettersNumbers<Character>(charList);
        ThreadClassLettersNumbers<Integer> numbers = new ThreadClassLettersNumbers<Integer>(numberList);

        Thread t1 = new Thread(letters);
        Thread t2 = new Thread(numbers);

        t1.start();
        t1.join();

        t2.start();
        t2.join();
    }


}


class ThreadClassLettersNumbers<E> implements Runnable {


    private final List<E> listToPrint;

    public ThreadClassLettersNumbers(List<E> listToPrint) {

        this.listToPrint = listToPrint;
    }

    @Override
    public void run() {
        for (E e : listToPrint) {
            System.out.println(e);
        }
    }
}


//class ThreadClassNumbers extends Thread {
//
//    @Override
//    public void run() {
//        for (int i = 0; i < 10; i++) System.out.println(i);
//    }
//}
//
//
//class ThreadClassLetters extends Thread {
//
//    @Override
//    public void run() {
//        for (int i = 0; i < 10; i++) System.out.println((char) (i + 65));
//    }
//}
