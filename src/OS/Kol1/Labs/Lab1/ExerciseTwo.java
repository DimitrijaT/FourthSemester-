package OS.Kol1.Labs.Lab1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Scanner;

public class ExerciseTwo {

    private static final long SEVEN_DAYS = 604_800_000;


    public static void  traversePngAndBmp(String path) throws FileNotFoundException {
        File file = new File(path);

        if (!file.exists()) throw new FileNotFoundException();
        if (!file.isDirectory()) throw new FileNotFoundException();

        File[] files = file.listFiles();

        for (File f : files) {
            if (f.isDirectory()) {
                traversePngAndBmp(f.getAbsolutePath());
            }
            if (f.isFile() && f.getName().endsWith(".jpg") || f.getName().endsWith(".bmp")) {
                Date fileDate = new Date(f.lastModified());
                Date currentDate = new Date(System.currentTimeMillis());
                Date result = new Date(currentDate.getTime() - fileDate.getTime());
                if (result.getTime() - SEVEN_DAYS <= 0){
                    System.out.println(f.getAbsolutePath());
                }
            }

        }


    }


    public static void main(String[] args) throws FileNotFoundException {

        Scanner s = new Scanner(System.in);
        String path = s.nextLine();
        traversePngAndBmp(path);
    }
}
