package OS.Kol1and2.AV6.Second;

public class Main {
    public static void main(String[] args) {
        Server server = new Server(Constants.serverPort,Constants.publicFolder);
        server.start();

        Client client = new Client(Constants.serverIP,Constants.serverPort, Constants.downloadFolder);
        client.start();


    }
}
