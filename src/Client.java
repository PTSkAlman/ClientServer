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
    private PrintWriter out;
    private ListenerThread in;

    public Client(String ip, int port) {
        try {
            socket = new Socket(ip, port);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        try {
            out = new PrintWriter(socket.getOutputStream(),true);
            in =
                    new ListenerThread(
                    new BufferedReader(
                    new InputStreamReader(socket.getInputStream())));
            Thread listener = new Thread(in);
            listener.start();
            boolean run = true;
            while (run) {
                sendMessage();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void sendMessage() {
        Scanner tgb = new Scanner(System.in);
        String msg = tgb.nextLine();
        out.println("Client: " + msg);
    }
}
