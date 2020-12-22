import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private JTextField productIDField;
    private JButton sendButton;
    private JTextArea productInfoTextArea;
    private JPanel cPanel;
    private JLabel productID;
    private JLabel productInfo;

    Socket socket;

    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;

    Worker worker;

    public Client(){
        try {
            socket = new Socket(InetAddress.getByName("127.0.0.1"), 12002);
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataInputStream = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    dataOutputStream.writeInt(Integer.parseInt(productIDField.getText()));
                } catch (UnknownHostException unknownHostException) {
                    unknownHostException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            });

        worker = new Worker();
        Thread workerThread = new Thread(worker);
        workerThread.start();

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    private class Worker implements Runnable {

        public void run() {
            try {
                while (true) {
                    String s = dataInputStream.readUTF();
                    productInfoTextArea.append(s+"\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        JFrame f = new JFrame("Client");
        f.setContentPane(new Client().cPanel);
        f.setMinimumSize(new Dimension(800, 800));
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);
    }
    }
