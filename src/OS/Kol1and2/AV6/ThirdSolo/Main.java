package OS.Kol1and2.AV6.ThirdSolo;



public class Main {
    public static void main(String[] args) throws InterruptedException {

        TCPServer server = new TCPServer(6969);
        server.start();
        Thread.sleep(200);
        TCPClient client = new TCPClient("localhost",6969);
        client.start();



    }
}
