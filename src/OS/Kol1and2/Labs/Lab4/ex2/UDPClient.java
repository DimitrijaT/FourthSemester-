package OS.Kol1and2.Labs.Lab4.ex2;

import java.io.IOException;
import java.net.*;
//DIMITRIJA TIMESKI 203235
//DIMITRIJA TIMESKI 203235
//DIMITRIJA TIMESKI 203235
//DIMITRIJA TIMESKI 203235
//DIMITRIJA TIMESKI 203235
//DIMITRIJA TIMESKI 203235
//DIMITRIJA TIMESKI 203235
public class UDPClient extends Thread {


    private String serverName;
    private int serverPort;

    private DatagramSocket socket;
    private InetAddress inetAddress;
    private String message;
    private byte[] buffer;
    private byte[] receiveBuffer;

    public UDPClient(String serverName, int serverPort, String message) {
        this.serverName = serverName;
        this.serverPort = serverPort;
        this.message = message;
        this.receiveBuffer = new byte[512];

        try {
            this.socket = new DatagramSocket();
            this.inetAddress = InetAddress.getByName(serverName);


        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        buffer = message.getBytes();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, this.inetAddress, this.serverPort);
        DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length, this.inetAddress, this.serverPort);
        try {
            socket.send(packet);
            packet = new DatagramPacket(buffer, buffer.length, inetAddress, serverPort);
            socket.receive(receivePacket);
            System.out.println(new String(receivePacket.getData(), 0, receivePacket.getLength()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        UDPClient udpClient = new UDPClient("194.149.135.49", 9753, "203235");
        udpClient.start();


    }
}
