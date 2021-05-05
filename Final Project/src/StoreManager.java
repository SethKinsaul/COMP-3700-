import javax.swing.*;

public class StoreManager {
    //path for sql database
    public static String path = "C:\\Users\\Seth\\Desktop\\Final Project\\data\\store.db";

    DatabaseManager dataAdapter = null;
    private static StoreManager instance = null;

    public static StoreManager getInstance() {
        if (instance == null) {
            instance = new StoreManager(path);
        }
        return instance;
    }

    private StoreManager(String dbfile) {
        dataAdapter = new SqlDataAdapter();
        dataAdapter.connect(dbfile);

    }

    public DatabaseManager getDataAdapter() {
        return dataAdapter;
    }

    public void setDataAdapter(DatabaseManager x) {
        dataAdapter = x;
    }


    public void run() {
        ManageUI ui = new ManageUI();
        ui.view.setVisible(true);
    }

    public static void main(String[] args) {
        if (args.length >= 0) { // having runtime arguments
            JFileChooser fc = new JFileChooser();
            if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                path = fc.getSelectedFile().getAbsolutePath();
            } else {
                path = JOptionPane.showInputDialog("Enter address of database server as host:port");
            }
            StoreManager.getInstance().run();
        }

    }
}