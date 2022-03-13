package OS.Semestar1.Labs.Lab1;

import java.io.File;
import java.io.FileNotFoundException;

public class ExerciseOne {


    public static File largestFileInfilePath(File filePath) throws FileNotFoundException {
        if (!filePath.exists()) throw new FileNotFoundException();
        if (!filePath.isDirectory()) throw new FileNotFoundException();

        File[] files = filePath.listFiles();

        File maxFile = null;
        for (File f : files) {

            if (f.isDirectory()) {
                File maxFromDirectory = largestFileInfilePath(f);
                if (maxFile == null || (maxFromDirectory != null && maxFromDirectory.length() > maxFile.length()))
                    maxFile = maxFromDirectory;
            }
            if (!f.isDirectory() && (maxFile == null || maxFile.length() <= f.length())) {
                maxFile = f;
            }
        }
        return maxFile;
    }


    public static void main(String[] args) throws FileNotFoundException {


        File filePath = new File("C:\\Users\\Public\\Documents\\FourthSemester\\src\\OS\\Semestar1\\Labs\\files");

        System.out.println(largestFileInfilePath(filePath).getAbsolutePath());


    }
}
