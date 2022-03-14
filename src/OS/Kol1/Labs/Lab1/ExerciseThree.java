package OS.Kol1.Labs.Lab1;

import java.io.*;

public class ExerciseThree {

    public static void readTheLinesAndSaveThem(File izvor, File dest) throws IOException {
        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            reader = new BufferedReader(new FileReader(izvor));
            writer = new BufferedWriter(new FileWriter(dest));

            String s = null;

            while ((s = reader.readLine()) != null) {

                if (Character.isDigit(s.charAt(0))) {
                    writer.write(s);
                    writer.newLine();
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) reader.close();
            if (writer != null) {
                writer.flush();
                writer.close();

            }
        }


    }


    public static void main(String[] args) throws IOException {


        File in = new File("C:\\Users\\Public\\Documents\\FourthSemester\\src\\OS\\Semestar1\\Labs\\Lab1\\izvor.txt");
        File out = new File("C:\\Users\\Public\\Documents\\FourthSemester\\src\\OS\\Semestar1\\Labs\\Lab1\\destinacija.txt");

        readTheLinesAndSaveThem(in, out);

    }
}
