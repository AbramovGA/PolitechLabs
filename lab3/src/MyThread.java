import java.util.*;


public class MyThread extends Thread{
    private final Multiplication parent;
    private int id;
    private double[][] Apart;
    private double[][] B;
    private int firstRaw;


    MyThread(int id, double[][] Apart, double[][] Bpart, int firstRaw, Multiplication parent){
        this.id = id;
        this.Apart = Apart;
        this.B = Bpart;
        this.firstRaw = firstRaw;
        this.parent = parent;
    }

    @Override
    public void run() {
        double[][] C=Multiply(Apart,B);
        parent.setRaws(C, firstRaw);
    }

    private static double[][] Multiply(double A[][], double B[][]){
        int stringsA=A.length;
        int columnsA=A[0].length;
        int stringsB=B.length;
        int columnsB=B[0].length;
        double C[][]=new double[stringsA][columnsB];
        for(int i=0;i<stringsA;i++){
            for(int j=0;j<columnsB;j++){
                C[i][j]=0;
                for(int k=0;k<columnsA;k++){
                    C[i][j]+=A[i][k]*B[k][j];
                }
            }
        }
        return C;
    }

}