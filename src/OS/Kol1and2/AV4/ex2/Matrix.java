package OS.Kol1and2.AV4.ex2;

import java.util.Scanner;

public class Matrix {

    int m;
    int n;

    double[][] matrix;


    public Matrix(int m, int n, double[][] matrix) {
        this.m = m;
        this.n = n;
        this.matrix = matrix;
    }

    public Matrix(int m, int n) {
        this.m = m;
        this.n = n;
        this.matrix = new double[m][n];
    }

    public void setEl(int i, int j, double value) {
        this.matrix[i][j] = value;

    }

    public double[] getRow(int i) {
        return this.matrix[i];
    }

    public double[] getColumn(int j){
        double[] column = new double[m];
        for (int i=0;i<this.m;i++){
            column[i] = matrix[i][j];
        }
        return column;
    }

    public void printMatrix(){
        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

    }

    public static Matrix buildFromStdIn(){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        Matrix matrix = new Matrix(m,n);
        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                System.out.printf("M[%d,%d]=",i,j);
                double value = scanner.nextDouble();
                matrix.setEl(i,j,value);
            }
        }
        return matrix;

    }

    public int getM() {
        return m;
    }

    public int getN() {
        return n;
    }
}
