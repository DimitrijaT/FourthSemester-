package OS.Kol1and2.AV6.First.tcp.server;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HttpWorkerThread extends Thread {

    private Socket socket;


    public HttpWorkerThread(Socket socket) {
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

            String line = null;
            StringBuilder sb = new StringBuilder();
            while (!(line = reader.readLine()).isEmpty()) {
                sb.append(line).append("\n");
                System.out.println(line);
            }
            RequestProcessor requestProcessor = RequestProcessor.of(sb.toString());
            writer.write("HTTP/1.1 200 OK\n\n");

            if (requestProcessor.getCommand().equals("GET") && requestProcessor.getUri().equals("/time")) {
                writer.printf("<html><head></head><body><h1>%s</h1></body></html>", LocalDateTime.now().format(DateTimeFormatter.ISO_TIME));

            } else {
                writer.printf("Hello world!");
            }
            writer.flush();


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
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
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}