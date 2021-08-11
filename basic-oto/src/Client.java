import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Client {
    JFrame frame = new JFrame("Client");
    JTextArea textArea = new JTextArea();
    JTextField inputText = new JTextField();

    String host;
    int port;
    Socket clientSocket;

    DataOutputStream outputStream;
    DataInputStream inputStream;

    public void showClientView() {
        textArea.setEditable(false);
        textArea.setText("클라이언트 화면 \n");
        frame.add("Center", textArea);
        frame.add("South", inputText);
        frame.setSize(300, 500);
        frame.setVisible(true);
    }

    public void throwSocket(String host, int port) {
        try {
            clientSocket = new Socket(host, port);
            System.out.println("Throw socket to server");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void connectInputStream() {
        try {
            inputStream = new DataInputStream(clientSocket.getInputStream());
            System.out.println("Client.connectInputStream");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void connectOutputStream() {
        try {
            outputStream = new DataOutputStream(clientSocket.getOutputStream());
            System.out.println("Client.connectOutputStream");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessageToServer(String message) {
        try {
            outputStream.writeUTF(message);
            System.out.println("To server : " + message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String receiveMessageToServer() {
        try {
            String receiveMessage = inputStream.readUTF();
            System.out.println("From server : " + receiveMessage);

            return receiveMessage;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {

        Client client = new Client();

        client.showClientView();

        client.throwSocket("localhost", 8081);
        client.connectInputStream();
        client.connectOutputStream();
    }
}
