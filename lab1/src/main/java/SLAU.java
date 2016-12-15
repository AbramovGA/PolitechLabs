import org.apache.commons.math3.linear.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by german on 29.09.16.
 */

public class SLAU {

    public static RealMatrix coefficients;
    public static RealVector constants;
    public static double[] answer;


    public static double[] solveit(double[][]matr, double[] vector){
        coefficients = new Array2DRowRealMatrix(matr, false);
        constants = new ArrayRealVector(vector);
        DecompositionSolver solver= new LUDecomposition(coefficients).getSolver();
        RealVector solution = solver.solve(constants);
        answer = solution.toArray();
        for (double d:answer) {
            System.out.println(d);
        }
        return answer;
    }

    public static void main(String[] args) throws FileNotFoundException{
        File matrix=new File("res/slauTest.txt");
        Scanner in=new Scanner(matrix);
        int rows=in.nextInt();
        int cols=in.nextInt();
        double[][] matr=new double[rows][cols];
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                matr[i][j]=in.nextInt();
            }
        }
        rows=in.nextInt();
        double[]vector=new double[rows];
        for(int i=0;i<rows;i++){
            vector[i]=in.nextInt();
        }
        solveit(matr, vector);
    }

}

