package OS.Kol1and2.Labs.Lab2.MajaThree;

import java.io.File;
import java.io.FileNotFoundException;

public class FileScanner extends Thread {

    private String fileToScan;
    private static Long counter = 0L;

    public FileScanner(String fileToScan) {
        this.fileToScan = fileToScan;
        counter++;
    }

    public static void printInfo(File file) throws FileNotFoundException {
        if (!file.exists()) throw new FileNotFoundException();
        if (file.isDirectory()) {
            System.out.println("dir: " + file.getName() + " - resenija " + file.listFiles().length);
        } else if (file.isFile()) {
            System.out.println("file: " + file.getName() + " " + file.length());
        }
    }

    public static Long getCounter() {
        return counter;
    }


    @Override
    public void run() {
        File file = new File(fileToScan);
        File[] files = file.listFiles();

        for (File f : files) {
            //if (f.isFile()) {
                try {
                    printInfo(f);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            //} else
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
        fileScanner.start();
        fileScanner.join();
        System.out.println("Number of threads that were created: " + FileScanner.getCounter());
    }
}