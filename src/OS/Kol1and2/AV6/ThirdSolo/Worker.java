package OS.Kol1and2.AV6.ThirdSolo;

import java.io.*;
import java.net.Socket;

public class Worker extends Thread {

    private Socket socket;

    public Worker(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader bufferedReader = null;
        PrintWriter writer = null;

        try {
            String s = null;
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            while (!(s = bufferedReader.readLine()).isEmpty()) {
                System.out.println(s);
            }
            System.out.println();


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
