package OS.Kol1and2.AV4.ex2;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Внеси ја првата матрица: ");
        Matrix m1 = Matrix.buildFromStdIn();
        m1.printMatrix();
        System.out.println("Внеси ја втората матрица: ");
        Matrix m2 = Matrix.buildFromStdIn();
        m2.printMatrix();
        if (m1.getN() != m2.getM()){
            throw new RuntimeException();
        }

        Matrix result = new Matrix(m1.getM(),m2.getN());

        List<MatrixMultiplicationThread> threadList = new ArrayList<>();

        for (int i=0;i<m1.getM();i++){
            for (int j=0;j<m2.getN();j++){
                MatrixMultiplicationThread thread = new MatrixMultiplicationThread(i,j,m1.getRow(i),m2.getColumn(j),result);
                threadList.add(thread);
            }
        }

        for (MatrixMultiplicationThread t : threadList) {
            t.start();
        }
        for (MatrixMultiplicationThread t : threadList) {
            t.join();
        }
        result.printMatrix();
    }
}
