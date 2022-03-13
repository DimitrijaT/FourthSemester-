package OS.Semestar1.AV25;

import OS.Semestar1.AV25.impl.IOStreamManagerImpl;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        String one = "C:\\Users\\Public\\Documents\\FourthSemester\\src\\OS\\Semestar1\\AV3\\files\\in.txt";
        String two = "C:\\Users\\Public\\Documents\\FourthSemester\\src\\OS\\Semestar1\\AV3\\files\\out.txt";

        File from = new File(one);
        File to = new File(two);

        IOStreamManager sm = new IOStreamManagerImpl();

//        sm.copyFileByUsingBuffer(one,two);
//        sm.copyFileByteByByte(from,to);
//        sm.printContentOfTxtFile(from,System.out);
//        sm.readContentFromStdInput(new FileOutputStream(to));
//        sm.writeToTextFile(to,"Srekjen Den!!!",true);
//        sm.readFileWithLineNumber(to,System.out);

//        sm.writeBinaryDataToBFile(to,3,5.6,"Dimitrija");
//
//        Object[] objects = new Object[3];
//        objects[0] = 1;
//        objects[1] = 2.2;
//        objects[2] = "Timeski";
//        sm.readBinaryDataFromBFile(to,objects);

//        sm.writeToRandomAccessFile(to);

        sm.rewriteInReverseFile(from,to);

    }
}
