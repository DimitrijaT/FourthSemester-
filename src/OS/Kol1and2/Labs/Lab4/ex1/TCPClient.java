package OS.Kol1and2.Labs.Lab4.ex1;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
//DIMITRIJA TIMESKI 203235
//DIMITRIJA TIMESKI 203235
//DIMITRIJA TIMESKI 203235
//DIMITRIJA TIMESKI 203235
//DIMITRIJA TIMESKI 203235
//DIMITRIJA TIMESKI 203235
//DIMITRIJA TIMESKI 203235
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
        Scanner scanner = null;
        PrintWriter writer = null;
        BufferedReader reader = null;

        try {
            socket = new Socket(serverName, serverPort);
            scanner = new Scanner(System.in);
            writer = new PrintWriter(socket.getOutputStream());
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));


            writer.println(Utils.LOGIN);
            writer.flush();

            String serverResponse = reader.readLine();
            System.out.println(serverResponse);
            if (serverResponse.equals(Utils.SERVER_ANSWER)) {
                writer.println(Utils.HELLO);
                writer.flush();
                serverResponse = reader.readLine();
                System.out.println(serverResponse);
                if (serverResponse.equals(Utils.SERVER_WELCOME)) {
                    new ClientPrinter(socket).start();
                    String message = null;
                    while (true) {
                        //System.out.println("Send message to: ");
                        //Utils.SEND_TO = scanner.nextLine();
                        //System.out.println("The Message Shall be: ");
                        message = scanner.nextLine();
                        writer.println(Utils.SEND_TO + message);
                        writer.flush();
                    }
                }
            }

            System.out.println(serverResponse);


        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (scanner != null) {
                scanner.close();
            }
            if (writer != null) {
                writer.close();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    public static void main(String[] args) {
        TCPClient client = new TCPClient("194.149.135.49", 9753);

        client.start();


    }
}
