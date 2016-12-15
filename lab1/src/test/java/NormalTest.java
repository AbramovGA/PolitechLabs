/**
 * Created by german on 08.12.16.
 */
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class NormalTest {
    private double[] vector;
    private double[][] matrix;
    private double[] answer;

    public NormalTest(double[] vector, double[][] matrix, double[] answer){
        this.vector=vector;
        this.answer=answer;
        this.matrix=matrix;
    }

    @Parameterized.Parameters(name = "{index}")
    public static Collection<Object[]> vectorData() {
        return Arrays.asList(new Object[][]{
                //with 3 variables
                {new double[]{2,-2,2},new double[][]{{2,1,1},{1,-1,0},{3,-1,2}},new double[]{-1.0,1.0,3.0}},
                //with 2 variables
                {new double[]{1,1},new double[][]{{1,0},{0,1}},new double[]{1,1}},

        });
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

    @Test
    public void SLAUtest() throws IOException {
        Assert.assertTrue(vectorEquals(answer, SLAU.solveit(matrix, vector)));
    }

}