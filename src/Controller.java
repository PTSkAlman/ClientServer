import javax.swing.*;

public class Controller {

    private Client client;
    private Server server;

    public Controller() {
        String[] startupOptions = {"Server", "Client"};

        int startupChoice = JOptionPane.showOptionDialog(null, "Do you want to start as server or client?",
                "Click a button",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, startupOptions, startupOptions[0]);
        if (startupChoice == 0) {

            server = new Server();

        } else if (startupChoice == 1) {

            String ip = (String) JOptionPane.showInputDialog(null,"IP?","Connect to..",JOptionPane.QUESTION_MESSAGE);
            int port = Integer.parseInt(JOptionPane.showInputDialog(null,"Port?","Connect to..",JOptionPane.QUESTION_MESSAGE));       ;
            client = new Client(ip, port);

        }
    }
}
