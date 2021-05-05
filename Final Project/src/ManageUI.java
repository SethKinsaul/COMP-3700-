import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageUI {
    public JFrame view;

    public JButton manageCustomersButton = new JButton("Manage Customers");
    public JButton manageProductsButton = new JButton("Manage Products");
    public JButton manageCheckoutButton = new JButton("Manage Checkout");
    public JTextArea messageTextArea = new JTextArea();

    public ManageUI() {
        this.view = new JFrame();

        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.setTitle("Store Management System - Manager View");
        view.setSize(400, 400);
        view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));

        JLabel title = new JLabel("Store Management System");

        title.setFont (title.getFont ().deriveFont (24.0f));
        view.getContentPane().add(title);

        JPanel panelButtons = new JPanel(new FlowLayout());
        panelButtons.add(manageCustomersButton);
        panelButtons.add(manageProductsButton);
        panelButtons.add(manageCheckoutButton);

        view.getContentPane().add(panelButtons);

        manageCustomersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManageCustomerUI ui = new ManageCustomerUI();
                ui.run();
            }
        });

        manageProductsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManageProductUI ui = new ManageProductUI();
                ui.run();
            }
        });

        manageCheckoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManageCheckoutUI ui = new ManageCheckoutUI();
                ui.run();
            }
        });
    }
}