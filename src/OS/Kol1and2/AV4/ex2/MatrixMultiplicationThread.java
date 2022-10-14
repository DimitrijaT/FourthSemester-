package OS.Kol1and2.AV4.ex2;

public class MatrixMultiplicationThread extends Thread{

    private int i;
    private int j;
    private double[] row;
    private double[] column;
    private Matrix result;

    public MatrixMultiplicationThread(int i, int j, double[] row, double[] column, Matrix result) {
        this.i = i;
        this.j = j;
        this.row = row;
        this.column = column;
        this.result = result;
    }

    @Override
    public void run() {
        if (column.length != row.length){
            throw new RuntimeException();
        }
        double value = 0.0;

        for (int i=0;i<column.length;i++){
            value += column[i] * row[i];
        }

        result.setEl(i,j,value);
    }
}
