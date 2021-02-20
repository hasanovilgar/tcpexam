import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TCPServer {
    public static void main(String[] args) throws Exception {
        readAsByte();
    }

    public static void readAsByte() throws Exception{
        ServerSocket ourFirstServerSocket = new ServerSocket(6789);

        while (true) {
            System.out.println("Yeni fayl gözləyirəm..");
            Socket connection = ourFirstServerSocket.accept();
            System.out.println("Yeni fayl gəldi..");

            DataInputStream dataStream = new DataInputStream(connection.getInputStream());

            byte[] arr = readMessage(dataStream);
            System.out.println("message length2="+arr.length);

            Files.write(Paths.get("sekil.jpg"), arr);
        }
    }

    public static byte[] readMessage(DataInputStream din) throws Exception{
        int msgLen = din.readInt();//1
        System.out.println("message length1="+msgLen);
        byte[] msg = new byte[msgLen];

        din.readFully(msg);
        return msg;
    }

}
