package OS.Kol1and2.AV6.ThirdSolo;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient extends Thread {

    private String serverName;
    private int serverPort;

    public TCPClient(String serverName, int serverPort) {

        this.serverName = serverName;
        this.serverPort = serverPort;
    }

    @Override
    public void run() {
        Socket socket = null;
        BufferedReader reader = null;
        PrintWriter writer = null;
        Scanner scanner = null;


        try {
            socket = new Socket(serverName, serverPort);
            scanner = new Scanner(System.in);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            String s = null;
            System.out.println("Send a message:");
            while (true) {

                s = scanner.nextLine();
                writer.println(s);
                writer.flush();


            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
