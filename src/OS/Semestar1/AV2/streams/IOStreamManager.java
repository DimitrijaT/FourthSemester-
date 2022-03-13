package OS.Semestar1.AV2.streams;

import java.io.*;

public interface IOStreamManager {

    void copyFileByteByByte(File from, File to) throws IOException;

    void printContentOfTxtFile(File f, PrintStream printer) throws IOException;

    void readContentFromStdInput(OutputStream to) throws IOException;

    void writeToTextFile(File to, String text, Boolean append) throws IOException;

    void memoryUnsafeTextFileCopy(File from, File to) throws IOException;

    void memorySafeTextFileCopy(File from, File to) throws IOException;

    void readFileWithLineNumber(File from, OutputStream outputStream) throws IOException;

    void writeBinaryDataToBFile(File to, Object... objects) throws IOException ;

    void readBinaryDataFromBFile(File from, Object... objects) throws IOException;

    void writeToRandomAccessFile(File from) throws IOException;

    void readFromRandomAccessFile(File from, PrintStream out) throws IOException;
}
