import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageCustomerUI {

    public JFrame view;

    public JButton loadCustomerButton = new JButton("Load Customer");
    public JButton saveCustomerButton = new JButton("Save Customer");

    public JTextField txtCustomerID = new JTextField(20);
    public JTextField txtName = new JTextField(20);
    public JTextField txtPhone = new JTextField(20);
    public JTextField txtAddress = new JTextField(20);

    public ManageCustomerUI() {
        this.view = new JFrame();

        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        view.setTitle("Manage Customer Information");
        view.setSize(600, 400);
        view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));

        JPanel panelButtons = new JPanel(new FlowLayout());
        panelButtons.add(loadCustomerButton);
        panelButtons.add(saveCustomerButton);
        view.getContentPane().add(panelButtons);

        JPanel line1 = new JPanel(new FlowLayout());
        line1.add(new JLabel("CustomerID "));
        line1.add(txtCustomerID);
        view.getContentPane().add(line1);

        JPanel line2 = new JPanel(new FlowLayout());
        line2.add(new JLabel("Name "));
        line2.add(txtName);
        view.getContentPane().add(line2);

        JPanel line3 = new JPanel(new FlowLayout());
        line3.add(new JLabel("Phone "));
        line3.add(txtPhone);
        view.getContentPane().add(line3);

        JPanel line4 = new JPanel(new FlowLayout());
        line4.add(new JLabel("Address "));
        line4.add(txtAddress);
        view.getContentPane().add(line4);

        saveCustomerButton.addActionListener(new SaveButtonListener());

        loadCustomerButton.addActionListener(new LoadButtonListerner());
    }
    class SaveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            Customer customer = new Customer();
            String id = txtCustomerID.getText();

            if (id.length() == 0) {
                JOptionPane.showMessageDialog(null, "CustomerID cannot be null!");
                return;
            }

            try {
                customer.mCustomerID = Integer.parseInt(id);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "CustomerID is invalid!");
                return;
            }

            String name = txtName.getText();
            if (name.length() == 0) {
                JOptionPane.showMessageDialog(null, "Customer name cannot be empty!");
                return;
            }

            customer.mName = name;

            String phone = txtPhone.getText();
            if (phone.length() == 0) {
                JOptionPane.showMessageDialog(null,  "Customer phone cannot be empty!");
                return;
            }

            customer.mPhone = phone;

            String address = txtAddress.getText();
            if (address.length() == 0) {
                JOptionPane.showMessageDialog(null,  "Customer address cannot be empty!");
                return;
            }

            customer.mAddress = address;

            int  res = StoreManager.getInstance().getDataAdapter().saveCustomer(customer);

            if (res == DatabaseManager.CUSTOMER_SAVE_FAILED)
                JOptionPane.showMessageDialog(null, "Customer is NOT saved successfully!");
            else
                JOptionPane.showMessageDialog(null, "Customer is SAVED successfully!");
        }
    }
    class LoadButtonListerner implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            Customer customer = new Customer();
            String id = txtCustomerID.getText();

            if (id.length() == 0) {
                JOptionPane.showMessageDialog(null, "CustomerID cannot be null!");
                return;
            }

            try {
                customer.mCustomerID = Integer.parseInt(id);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "CustomerID is invalid!");
                return;
            }

            customer = StoreManager.getInstance().getDataAdapter().loadCustomer(customer.mCustomerID);

            if (customer == null) {
                JOptionPane.showMessageDialog(null, "Customer NOT exists!");
            } else {
                txtName.setText(customer.mName);
                txtPhone.setText(customer.mPhone);
                txtAddress.setText(customer.mAddress);
            }
        }
    }
    public void run() {
        view.setVisible(true);
    }
}
