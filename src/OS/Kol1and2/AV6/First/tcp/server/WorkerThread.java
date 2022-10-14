package OS.Kol1and2.AV6.First.tcp.server;

import java.io.*;
import java.net.Socket;

public class WorkerThread extends Thread {

    private Socket socket;

    public WorkerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        BufferedReader reader = null;
        PrintWriter writer = null;

        try {


            System.out.printf("Connected: %s:%d ", socket.getInetAddress(), socket.getPort());

            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));


            String s = null;

            while (!(s = reader.readLine()).isEmpty()) {
                System.out.printf("New message from %s:%d:%s\n", socket.getInetAddress(), socket.getPort(), s);
                //System.out.println(s);
                writer.write(s);
                writer.flush();
            }

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
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (writer != null) {
                writer.close();
            }
        }


    }
}
