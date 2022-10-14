package OS.Kol1and2.AV6.Second;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
    int port;
    ServerSocket serverSocket;
    String publicFolder;

    public Server(int port,String publicFolder) {
        this.port = port;
        this.publicFolder = publicFolder;
    }

    @Override
    public void run() {
        try {
            // 0000 <- javen
            // localhost
            serverSocket = new ServerSocket(port);
            while (true) {
                Socket socket = this.serverSocket.accept();
                SocketWorker socketWorker = new SocketWorker(socket, publicFolder);
                socketWorker.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
