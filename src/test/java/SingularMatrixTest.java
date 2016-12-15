import org.apache.commons.math3.linear.SingularMatrixException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;


/**
 * Created by german on 07.10.16.
 */
public class SingularMatrixTest {
    static double[][] matrix;
    static double vector[];
    static double answer[];
    @Before
    public void prepare(){
        double[][] matr={{0,0,0},{0,0,0},{0,0,0}};
        double[] vect={0,0,0};
        double[] answ={0,0,0};
        matrix=matr;
        vector=vect;
        answer=answ;
    }

    boolean vectorEquals(double[] a, double[] b){
        if(a.length!=b.length)
            return false;
        for(int i=0;i<a.length;i++){
            if(a[i]!=b[i])
                return false;
        }
        return true;
    }

    @Test(expected = SingularMatrixException.class)
    public void test() {
        assertTrue(vectorEquals(answer,SLAU.solveit(matrix,vector)));
    }
}
