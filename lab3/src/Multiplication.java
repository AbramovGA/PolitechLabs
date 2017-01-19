import java.util.*;


class Multiplication {
    private int THREAD_COUNT = 3;

    private double[][] A;
    private double[][] B;
    private double[][] result;
    private int countOfRunningThread = 0;

    Multiplication(double[][] A, double[][] B) {
        this.A = A;
        this.B = B;
        this.result = new double[A.length][B[0].length];
    }

    public void start() {
        int delta = A.length/THREAD_COUNT;
        double[][] Apart;
        List<Thread> threads = new ArrayList<>();
        int rawsCount;
        int totalRawNum=0;
        double[][] temp;
        for(int i=0; i<THREAD_COUNT; i++){
            countOfRunningThread++;
            rawsCount = (i==THREAD_COUNT-1?A.length-totalRawNum:delta);
            Apart=new double[rawsCount][A[0].length];
            for(int j=0;j<rawsCount;j++){
                Apart[j]=A[totalRawNum++];
            }
            threads.add(new MyThread(i, Apart, B, totalRawNum-rawsCount, this));
        }

        System.out.println("Start " + countOfRunningThread + " thread");
        long startTime = System.currentTimeMillis();
        for(Thread tread : threads){
            tread.run();
        }
        for(Thread thread : threads){
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Result: ");
        for(int i=0;i<result.length;i++){
            for(int j=0;j<result[0].length;j++){
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }

    }

    synchronized void setRaws(double[][] C, int firstRaw) {
        for(int i=0;i<C.length;i++){
            result[firstRaw+i]=C[i];
        }
    }
}
