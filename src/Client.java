import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private Socket socket;
    private String ip;
    private int port;

    public Client(String ip, int port) {
        try {
            socket = new Socket(ip, port);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            ListenerThread in = new ListenerThread(new BufferedReader(new InputStreamReader(socket.getInputStream()
             )));
            Thread listener = new Thread(in);
            listener.start();

            Scanner tgb = new Scanner(System.in);

            //out.close();
            //socket.close();
            //System.out.println("Done!");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
