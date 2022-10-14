package OS.Kol1and2.Labs.Lab4.ex1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
//DIMITRIJA TIMESKI 203235
//DIMITRIJA TIMESKI 203235
//DIMITRIJA TIMESKI 203235
//DIMITRIJA TIMESKI 203235
//DIMITRIJA TIMESKI 203235
//DIMITRIJA TIMESKI 203235
//DIMITRIJA TIMESKI 203235
public class ClientPrinter extends Thread {

    private Socket socket;

    public ClientPrinter(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String s = null;
            while (!(s = reader.readLine()).isEmpty()) {
                System.out.println(s);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
