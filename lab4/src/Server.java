import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.InterfaceAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;


class Server {

    void run() throws IOException {

        Selector selector = Selector.open();
        ServerSocketChannel socket = ServerSocketChannel.open();
        InetSocketAddress addr = new InetSocketAddress("localhost", 8000);


        socket.bind(addr);

        socket.configureBlocking(false);

        int ops = socket.validOps();
        SelectionKey selectKey = socket.register(selector, ops, null);

        log("Server running");
        while (true) {

            log("waiting message..");
            selector.select();

            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> it = keys.iterator();

            while (it.hasNext()) {
                SelectionKey key = it.next();

                if (key.isAcceptable()) {
                    SocketChannel client = socket.accept();

                    client.configureBlocking(false);

                    client.register(selector, SelectionKey.OP_READ);
                    log("Connection Accepted: " + client.getLocalAddress() + "\n");
                } else if (key.isReadable()) {
                    SocketChannel client = (SocketChannel) key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(256);
                    client.read(buffer);
                    String req = new String(buffer.array()).trim();

                    log("get " + req);

                    if ("disconnect".equals(req)) {
                        key.channel().close();
                    } else {
                        new Thread(() -> {
                            byte[] response = readReq(req).getBytes();
                            ByteBuffer bufferResponse = ByteBuffer.wrap(response);
                            try {
                                client.write(bufferResponse);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }).run();
                    }
                }

                it.remove();
            }
        }
    }


    private static String readReq(String req){
        Scanner in=new Scanner(req);
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

        return writeStr(multiply(A, B));
    }

    private static String writeStr(double[][] Matrix){
        String response = new String(String.valueOf(Matrix.length) + " " + String.valueOf(Matrix[0].length) + " ");
        for(int i=0;i<Matrix.length;i++){
            for(int j=0;j<Matrix[0].length;j++){
                response = response.concat(" " + String.valueOf(Matrix[i][j]));
            }
        }
        return response;
    }

    private static double[][] multiply(double A[][], double B[][]){
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

    private void log(String str) {
        System.out.println(str);
    }

    public static void main(String[] args) throws IOException {
        new Server().run();
    }

}