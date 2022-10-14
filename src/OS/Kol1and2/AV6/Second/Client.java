package OS.Kol1and2.AV6.Second;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client extends Thread {

    private String serverName;
    private int port;
    private String downloadFolder;


    public Client(String serverName, int serverPort, String downloadFolder) {
        this.serverName = serverName;
        this.port = serverPort;
        this.downloadFolder = downloadFolder;
    }

    @Override
    public void run() {
        Socket socket = null;
        DataInputStream dis;
        DataOutputStream dos;
        try {
            socket = new Socket(serverName, port);

            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());

            List<String> files = listFiles(dis, dos);
            showFilesToUser(files);
            String fileName = chooseFileToDownload(files);
            initFileDownload(dis, dos, fileName);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private List<String> listFiles(DataInputStream dis, DataOutputStream dos) throws IOException {
        dos.writeUTF(Constants.listFilesCmd);
        String s = dis.readUTF();
        List<String> files = new ArrayList<>();
        while (!(s = dis.readUTF()).equals(Constants.listFilesEndCmd)) {
            files.add(s);
        }
        return files;
    }

    private void showFilesToUser(List<String> files) {
        for (int i = 0; i < files.size(); i++) {
            System.out.printf("%d %s\n", i + 1, files.get(i));
        }
    }

    private String chooseFileToDownload(List<String> files) {
        System.out.println("Please choose the id of file to download");
        Scanner scanner = new Scanner(System.in);
        int fileNumber = scanner.nextInt();
        return files.get(fileNumber - 1);
    }

    public void initFileDownload(DataInputStream dis, DataOutputStream dos, String fileName) throws IOException {
        dos.writeUTF(Constants.downloadFileName + fileName);
        String response = dis.readUTF();
        if (response.startsWith(Constants.downloadFileStart)) {
            System.out.println("File Download is starting...");
            BufferedWriter writer = null;
            try {
                writer = new BufferedWriter(new FileWriter(String.format("%s\\%s", downloadFolder, fileName)));
                while (!((response = dis.readUTF()).equals(Constants.downloadFileFinish))) {
                    writer.write(response);
                    writer.newLine();
                }
            } finally {
                if (writer != null) {
                    writer.flush();
                    writer.close();
                }
            }
        }
        System.out.println("File Download finished!");

    }
}
