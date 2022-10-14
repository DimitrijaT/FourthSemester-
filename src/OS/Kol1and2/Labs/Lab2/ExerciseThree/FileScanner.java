package OS.Kol1and2.Labs.Lab2.ExerciseThree;

import java.io.File;
import java.io.FileNotFoundException;

public class FileScanner extends Thread {

    private final String fileToScan;
    private static Long counter = 0L;

    public FileScanner(String fileToScan) {
        this.fileToScan = fileToScan;
        counter++;
    }

    public static void printInfo(File file) throws FileNotFoundException {

        //dir: lab1 - reshenija 100 (dir за директориуми, името на директориумот и број на фајлови)
        //file: spisok.pdf 29198 (file за обични фајлови, име на фајл и големина)
        if (!file.exists()) {
            throw new FileNotFoundException();
        }

        if (file.isDirectory()) {
            System.out.printf("dir: %s %d\n", file.getName(), file.listFiles().length);
        } else if (file.isFile()) {
            System.out.printf("file: %s %d\n", file.getName(), file.length());
        }

    }

    public static Long getCounter() {
        return counter;
    }


    public void run() {

        File file = new File(fileToScan);
        File[] files = file.listFiles();


        for (File f : files) {

//            if (f.isFile()) {
            try {
                FileScanner.printInfo(f);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
//            }
            if (f.isDirectory()) {

                FileScanner fileScanner = new FileScanner(f.getAbsolutePath());
                fileScanner.start();

                try {
                    fileScanner.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        String FILE_TO_SCAN = "C:\\Users\\Public\\Documents\\FourthSemester\\src\\OS\\Kol1\\Labs\\files";

        FileScanner fileScanner = new FileScanner(FILE_TO_SCAN);

        //long startTime = System.currentTimeMillis();
        fileScanner.start();
        fileScanner.join();
        //long endTime = System.currentTimeMillis();
        //System.out.println(endTime-startTime);
        System.out.println("Number of threads created:" + FileScanner.getCounter());

    }
}