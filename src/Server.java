import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    private final int port = 1337;
    private ServerSocket serverSocket;
    private Socket socket;
    private PrintWriter out;
    private ListenerThread in;

    public Server() {
        try {
            serverSocket = new ServerSocket(port);
            listen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listen() throws IOException {
        while (true) {
            System.out.println("Waiting for connections!");
            socket = serverSocket.accept();

            out = new PrintWriter(socket.getOutputStream(), true);
            in =
                    new ListenerThread(
                    new BufferedReader(
                    new InputStreamReader(socket.getInputStream())));
            Thread listener = new Thread(in);
            listener.start();
            boolean run = true;
            while(run) {
                sendMessage();
            }
        }
    }

    private void sendMessage() {
        Scanner tgb = new Scanner(System.in);
        String msg = tgb.nextLine();
        out.println("Server: " + msg);
    }
}
