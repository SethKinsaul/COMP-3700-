import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerUI {

    public User user = null;
    public JFrame view;
    //Buttons
    public JButton makePurchaseButton = new JButton("Make a Purchase");
    public JButton seachProductButton = new JButton("Search Product");
    public JButton checkoutButton = new JButton("Checkout");
    public JButton manageCustomers = new JButton("Manage Customers");
    public JButton manageProducts = new JButton("Manage Products");

    public CustomerUI(User user) {

        this.user = user;

        this.view = new JFrame();

        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.setTitle("Store Management System - Customer View");
        view.setSize(1000, 600);
        view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));

        JLabel title = new JLabel("Store Management System");

        title.setFont (title.getFont ().deriveFont (24.0f));
        view.getContentPane().add(title);

        JPanel panelUser = new JPanel(new FlowLayout());
        panelUser.add(new JLabel("Fullname: " + user.mFullname));
        panelUser.add(new JLabel("CustomerID: " + user.mCustomerID));

        view.getContentPane().add(panelUser);

        JPanel panelButtons = new JPanel(new FlowLayout());
        panelButtons.add(makePurchaseButton);
        panelButtons.add(seachProductButton);
        panelButtons.add(manageCustomers);
        panelButtons.add(checkoutButton);
        panelButtons.add(manageProducts);

        view.getContentPane().add(panelButtons);

        makePurchaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AddPurchaseUI ui = new AddPurchaseUI();
                ui.run();
            }
        });

        seachProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ProductSearchUI ui = new ProductSearchUI(user);
                ui.view.setVisible(true);
            }
        } );

        checkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManageCheckoutUI ui = new ManageCheckoutUI();
                ui.view.setVisible(true);
            }
        });

        manageCustomers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManageCustomerUI ui = new ManageCustomerUI();
                ui.view.setVisible(true);
            }
        });
        manageProducts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManageProductUI ui = new ManageProductUI();
                ui.view.setVisible(true);
            }
        });

    }
}