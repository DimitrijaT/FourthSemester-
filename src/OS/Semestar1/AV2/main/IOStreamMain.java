package OS.Semestar1.AV2.main;

import OS.Semestar1.AV2.streams.IOStreamManager;
import OS.Semestar1.AV2.streams.impl.IOStreamManagerImpl;

import java.io.File;
import java.io.IOException;

public class IOStreamMain {
    public static void main(String[] args) throws IOException {
        String filePath = "C:\\Users\\Public\\Documents\\FourthSemester\\src\\OS\\Semestar1\\AV2\\files\\in.txt";
        String filePathDest = "C:\\Users\\Public\\Documents\\FourthSemester\\src\\OS\\Semestar1\\AV2\\files\\out.txt";

        IOStreamManager manager = new IOStreamManagerImpl();
        manager.printContentOfTxtFile(new File(filePath),System.out);
        manager.writeToTextFile(new File(filePathDest),"RANDOM TEXT", true);
    }
}
