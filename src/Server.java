import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private final int port = 1337;
    private ServerSocket serverSocket;
    private Socket socket;

    public Server() {
        try {
            serverSocket = new ServerSocket(port);
            while (true) {
                System.out.println("Waiting for connections!");
                socket = serverSocket.accept();

                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                ListenerThread in =
                        new ListenerThread(
                        new BufferedReader(
                        new InputStreamReader(socket.getInputStream())));
                Thread listener = new Thread(in);
                listener.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
