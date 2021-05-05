import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageCheckoutUI {
    public JFrame view;

    JButton makePurchaseButton = new JButton("Make Purchase");

    public ManageCheckoutUI() {
        this.view = new JFrame();

        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        view.setTitle("Manage Checkout");
        view.setSize(1000, 600);
        view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));

        JPanel panelButtons = new JPanel(new FlowLayout());
        panelButtons.add(makePurchaseButton);

        makePurchaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AddPurchaseUI ui = new AddPurchaseUI();
                ui.run();
            }
        });
    }
    public void run() {
        view.setVisible(true);
    }
}

