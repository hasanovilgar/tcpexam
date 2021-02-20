import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class TCPClient {
    public static void main(String[] args) throws Exception {
        System.out.println("Adınızı daxil edin:");
        String name = new Scanner(System.in).nextLine();
        System.out.println("Soyadınızı daxil edin:");
        String surname = new Scanner(System.in).nextLine();

        System.out.println("Zəhmət olmasa göndərmək istədiyiniz faylın yerləşdiyi keçidi qeyd edin:");
        String path = new Scanner(System.in).nextLine();

        System.out.println("Bu faylı göndərmək istədiyiniz şəxsin(serverin) ip və portunu daxil edin:");
        String ipPort = new Scanner(System.in).nextLine();
        String[] arr = ipPort.split(":");
        String ip = arr[0];
        int port =
                Integer.parseInt(arr[1]);

        Socket socket = new Socket(ip, port);
        OutputStream outputStream = socket.getOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

        byte[] bytes = Files.readAllBytes(Paths.get(path));
        dataOutputStream.writeInt(bytes.length);
        dataOutputStream.write(bytes);
        socket.close();
        System.out.println("Uğurla göndərildi!");
    }
}
