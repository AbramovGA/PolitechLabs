import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Scanner;


class Client {
    void run() throws IOException, InterruptedException {

        InetSocketAddress addr = new InetSocketAddress("localhost", 8000);
        SocketChannel client = SocketChannel.open(addr);


        log("Connecting to Server on port 1111...");

        ArrayList<String> requestMessages = new ArrayList<String>();

        // create a ArrayList with companyName list
        requestMessages.add(read("res/mat1.txt"));
        requestMessages.add(read("res/mat2.txt"));
        requestMessages.add(read("res/mat3.txt"));
        requestMessages.add("disconnect");

        for (String request : requestMessages) {

            byte[] requestMessage = request.getBytes();
            ByteBuffer bufferRequest = ByteBuffer.wrap(requestMessage);
            client.write(bufferRequest);
            log("sending: " + request);
            bufferRequest.clear();

            ByteBuffer bufferResponse = ByteBuffer.allocate(256);
            try {
                client.read(bufferResponse);
            } catch (Exception e){
                //e.printStackTrace();
                return;
            }
            String pi =  new String(bufferResponse.array()).trim();

            log(pi);
            Thread.sleep(100);
        }
        client.finishConnect();
        client.close();
    }

    private static String read(String path) throws FileNotFoundException {
        File matrixes=new File(path);
        String request=new String("");
        Scanner in=new Scanner(matrixes);

        while(in.hasNext()){
            request = request.concat(in.nextLine()+ " ");
        }
        return request;
    }

    private static void log(String str) {
        System.out.println(str);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        new Client().run();
    }
}