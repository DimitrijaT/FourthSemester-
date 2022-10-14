package OS.Kol1and2.AV6.ThirdSolo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer extends Thread{

    private int port;

    public TCPServer(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        ServerSocket serverSocket = null;
        System.out.println("TCP is starting...");
        try{
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.err.println("Socket Server failed to start.");
            e.printStackTrace();
            return;
        }
        System.out.println("TCP Server is started!");
        System.out.println("Waiting for connections...");
        while(true){

            Socket socket = null;

            try{
                socket = serverSocket.accept();
                new Worker(socket).start();

            } catch (IOException e) {
                e.printStackTrace();
            }


        }


    }
}
