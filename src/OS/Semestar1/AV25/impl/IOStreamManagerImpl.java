package OS.Semestar1.AV25.impl;

import OS.Semestar1.AV25.IOStreamManager;

import java.io.*;

public class IOStreamManagerImpl implements IOStreamManager {

    /**
     * Испитна задача што се паднала!!!!!!!!
     */


    @Override
    public void rewriteInReverseFile(File from, File to) throws IOException {

        RandomAccessFile rafInput = null;
        RandomAccessFile rafOutput = null;

        try {
            rafInput = new RandomAccessFile(from, "r");
            rafOutput = new RandomAccessFile(to, "rw");
            long total = rafInput.length();

            while (total > 0){
                total = total - 1;
                rafInput.seek(total);
                //.read  a byte
                //.write a byte
                //total <-> how many bytes in rafInput
                rafOutput.write(rafInput.read());
            }
        } finally {
            if (rafInput != null) {
                rafInput.close();
            }
            if (rafOutput != null) {
                rafOutput.close();
            }
        }


    }


    /**
     * Ова е многу бавно решение за копирање од еден во друг фајл!
     */

    @Override
    public void copyFileByteByByte(File from, File to) throws IOException {

        FileInputStream fis = null;
        FileOutputStream fos = null;

        long startTime = System.currentTimeMillis();

        try {
            fis = new FileInputStream(from);
            fos = new FileOutputStream(to);
            int c = -1;
            while ((c = fis.read()) != -1) {
                fos.write(c);

            }
        } catch (IOException exception) {
            System.out.println("copyFileByteByByte exception");
        } finally {
            if (fis != null) fis.close();
            if (fos != null) {
                fos.flush();
                fos.close();
            }
        }


        long endTime = System.currentTimeMillis();

        System.out.println(endTime - startTime);

    }

    @Override
    public void copyFileByUsingBuffer(String from, String to) throws IOException {

        BufferedReader reader = null;
        BufferedWriter writer = null;

        long startTime = System.currentTimeMillis();

        try {
//            reader = new BufferedReader(new FileReader(from));
//            writer = new BufferedWriter(new FileWriter(to));
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(from)));
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(to)));

            String s = null;

            while ((s = reader.readLine()) != null) {
                writer.write(s);
                writer.newLine();

            }
        } catch (IOException e) {
            System.out.println("copyFileByUsingBuffer exception");
        } finally {
            if (reader != null) reader.close();
            if (writer != null) {
                writer.flush();
                writer.close();
            }
        }

        long endTime = System.currentTimeMillis();

        System.out.println(endTime - startTime);

    }

    @Override
    public void readFromRandomAccessFile(File from, PrintStream out) throws IOException {
        RandomAccessFile raf = null;

        try {
            raf = new RandomAccessFile(from, "r");

            for (int i = 0; i < 10; i++) {
                System.out.println(raf.readDouble());
            }
            System.out.println(raf.readUTF());


        } finally {
            if (raf != null) raf.close();
        }
    }

    @Override
    public void writeToRandomAccessFile(File from) throws IOException {
        RandomAccessFile raf = null;

        try {
            raf = new RandomAccessFile(from, "rw");

            for (int i = 0; i < 10; i++) {
                raf.writeDouble(3.14);
            }
            raf.writeUTF("Zdravo Mamo");
            raf.seek(0);

            for (int i = 0; i < 10; i++) {
                System.out.println(raf.readDouble());
            }
            System.out.println(raf.readUTF());


        } finally {
            if (raf != null) raf.close();
        }


    }

    @Override
    public void readBinaryDataFromBFile(File from, Object... objects) throws FileNotFoundException, IOException {
        DataInputStream dis = null;
        try {
            dis = new DataInputStream(new FileInputStream(from));

            for (Object obj : objects) {
                if (obj instanceof Integer) {
                    obj = dis.readInt();
                } else if (obj instanceof Double) {
                    obj = dis.readDouble();
                } else if (obj instanceof String) {
                    obj = dis.readUTF();
                } else if (obj instanceof Boolean) {
                    obj = dis.readBoolean();
                }

                System.out.println(obj);
            }
        } finally {
            if (dis != null) {
                dis.close();
            }
        }
    }

    @Override
    public void writeBinaryDataToBFile(File to, Object... objects) throws IOException {
        DataOutputStream dos = null;
        try {
            dos = new DataOutputStream(new FileOutputStream(to));

            for (Object obj : objects) {
                if (obj instanceof Integer) {
                    dos.writeInt((Integer) obj);
                } else if (obj instanceof Double) {
                    dos.writeDouble((Double) obj);
                } else if (obj instanceof String) {
                    dos.writeUTF((String) obj);
                } else if (obj instanceof Boolean) {
                    dos.writeBoolean((Boolean) obj);
                }
            }
        } finally {
            if (dos != null) {
                dos.flush();
                dos.close();
            }
        }
    }

    @Override
    public void readFileWithLineNumber(File from, OutputStream is) throws IOException {

        BufferedReader reader = null;
        PrintWriter writer = null;
        try {
            reader = new BufferedReader(new FileReader(from));
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(is)));

            String s = null;
            int row = 0;

            while ((s = reader.readLine()) != null) {
                writer.printf("%d %s\n", row++, s);
            }

        } finally {
            if (reader != null) reader.close();
            if (writer != null) {
                writer.flush();
                writer.close();
            }
        }


    }

    @Override
    public void memorySafeTextFileCopy(File from, File to) throws FileNotFoundException, IOException {

        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {
            reader = new BufferedReader(new FileReader(from));
            writer = new BufferedWriter(new FileWriter(to));
            String s = null;
            while ((s = reader.readLine()) != null) {
                writer.write(s);
            }


        } catch (IOException e) {
            System.out.println("memoryUnsafeTextFileCopy exception");
        } finally {
            if (reader != null) reader.close();
            if (writer != null) {
                writer.flush();
                writer.close();
            }
        }

    }

    @Override
    public void memoryUnsafeTextFileCopy(File from, File to) throws FileNotFoundException, IOException {

        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {
            reader = new BufferedReader(new FileReader(from));

            StringBuilder sb = new StringBuilder();

            String s = null;
            while ((s = reader.readLine()) != null) {
                sb.append(s);
            }
            writer = new BufferedWriter(new FileWriter(to));
            writer.write(sb.toString());
        } catch (IOException e) {
            System.out.println("memoryUnsafeTextFileCopy exception");
        } finally {
            if (reader != null) reader.close();
            if (writer != null) {
                writer.flush();
                writer.close();
            }
        }


    }

    @Override
    public void writeToTextFile(File to, String text, Boolean append) throws IOException {
        BufferedWriter writer = null;

        try {

            writer = new BufferedWriter(new FileWriter(to, append));
            writer.write(text);


        } finally {
            if (writer != null) {
                writer.flush();
                writer.close();
            }
        }
    }

    @Override
    public void readContentFromStdInput(OutputStream to) throws IOException {
        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {

            reader = new BufferedReader(new InputStreamReader(System.in));
            writer = new BufferedWriter(new OutputStreamWriter(to));
            String line = null;

            /**
             * readLine() waits for user input!!!!!!!!!!!!!!
             */
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
        } finally {
            if (reader != null) reader.close();
            if (writer != null) {
                writer.flush();
                writer.close();
            }
        }


    }

    @Override
    public void printContentOfTxtFile(File f, PrintStream printer) throws IOException {

        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(f));

            String s = null;
            while ((s = reader.readLine()) != null) {
                printer.println(s);
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
            printer.flush();
        }


    }
}