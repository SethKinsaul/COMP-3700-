import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private JTextArea messageTextArea;
    private JPanel mainPanel;
    private ServerSocket serverSocket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    Worker worker;

    public Server() {
        try {
            serverSocket = new ServerSocket(12002);
            Socket socket = serverSocket.accept();

            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        worker = new Worker();
        Thread workerThread = new Thread(worker);
        workerThread.start();
    }

    private class Worker implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    int productID = dataInputStream.readInt();

                    messageTextArea.append("Receieved a product id from a client " + productID + "\n");

                    String productInformation = SelectApp.getInstance().selectProductTable(String.valueOf(productID));

                    if (productInformation.length() < 1) {
                        dataOutputStream.writeUTF("Product is not found");
                    }
                    else {
                        dataOutputStream.writeUTF("The product information is ready here:\nProductID, Name, Price, Quantitiy\n" + productInformation);
                    }
                    }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Server");
        frame.setContentPane(new Server().mainPanel);
        frame.setMinimumSize(new Dimension(800, 800));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

