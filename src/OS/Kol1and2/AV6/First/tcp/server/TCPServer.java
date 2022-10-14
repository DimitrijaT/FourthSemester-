package OS.Kol1and2.AV6.First.tcp.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer extends Thread {

    private int port;

    public TCPServer(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        System.out.println("TCP is starting...");


        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.err.println("Socket Server failed to start.");
            return;
        }
        System.out.println("TCP Server is started!");
        System.out.println("Waiting for connections...");
        while (true) {
            Socket socket = null;
            try {
                socket = serverSocket.accept(); //cheka klient da se prikluci
                new WorkerThread(socket).start(); //pocni konkurentno da raboti worker thread
                //new HttpWorkerThread(socket).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        TCPServer tcpServer = new TCPServer(9000);
        tcpServer.start();
    }
}

