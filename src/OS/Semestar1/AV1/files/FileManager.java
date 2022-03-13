package OS.Semestar1.AV1.files;

import OS.Semestar1.AV1.exceptions.FileExistsException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Date;

public interface FileManager {

    void createNewFile(String file) throws IOException, FileExistsException;

    File[] getFilesInFolder(File file) throws FileNotFoundException;

    void printFileNames(File file, PrintStream writer) throws FileNotFoundException;

    String getAbsolutePath(String relativePath) throws FileNotFoundException;

    long getFileSize(String file) throws FileNotFoundException;

    void printFilePermissions(File f, PrintStream writer) throws FileNotFoundException;

    void createFolder(String folder) throws FileExistsException;

    void renameFile(File src, File dest) throws FileExistsException, FileNotFoundException;

    Date getLastModified(String filePath) throws FileNotFoundException;

    boolean deleteFolder(File folder) throws FileNotFoundException;

    File[] filterImagesFilesInDir(String dirPath) throws FileNotFoundException;

    void filterImagesFilesInDirRec(File file, PrintStream out) throws FileNotFoundException;
}
