package OS.Kol1and2.AV6.Second;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SocketWorker extends Thread {

    Socket socket;
    private String publicFolder;

    public SocketWorker(Socket socket, String publicFolder) {
        this.socket = socket;
        this.publicFolder = publicFolder;
    }

    @Override
    public void run() {
        DataInputStream dis = null;
        DataOutputStream dos = null;

        try {
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
            String command = dis.readUTF();
            if (Constants.listFilesCmd.equals(command)) {
                System.out.println("Server:List Files command received!");
                List<String> stringList = listFiles();
                dos.writeUTF(Constants.listFilesCmd);
                for (String s : stringList){
                    dos.writeUTF(s);
                }
                dos.writeUTF(Constants.listFilesEndCmd);

            }
            String command2 = dis.readUTF();

            if (command2.startsWith(Constants.downloadFileName)){
                System.out.println("Server:Download File command received!");
                String filename = command2.split("=")[1];
                dos.writeUTF(Constants.downloadFileStart);
                System.out.println("Server:File downloading is starting...");
                BufferedReader reader = null;
                try{
                    reader = new BufferedReader(new FileReader(String.format("%s\\%s",publicFolder,filename)));
                    String line = null;

                    while ((line = reader.readLine()) != null){
                        dos.writeUTF(line);
                    }
                    dos.writeUTF(Constants.downloadFileFinish);
                }finally {
                    if (reader != null){
                        reader.close();
                    }
                }
                System.out.println("Server:File downloading finished...");


            }







        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    private List<String> listFiles() {
        File publicFolderFile = new File(this.publicFolder);
        return Arrays.stream(publicFolderFile.listFiles())
                .filter(File::isFile)
                .map(File::getName)
                .collect(Collectors.toList());


    }

}
