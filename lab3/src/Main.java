import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by germanium on 13.01.17.
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File matrix=new File("res/matrixes.txt");
        Scanner in=new Scanner(matrix);
        int rows=in.nextInt();
        int cols=in.nextInt();
        double[][] A=new double[rows][cols];
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                A[i][j]=in.nextInt();
            }
        }
        rows=in.nextInt();
        cols=in.nextInt();
        double[][] B=new double[rows][cols];
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                B[i][j]=in.nextInt();
            }
        }
        new Multiplication(A, B).start();
    }
}
