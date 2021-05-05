import java.util.List;

public interface DatabaseManager {

    public static final int CONNECTION_OPEN_OK = 100;
    public static final int CONNECTION_OPEN_FAILED = 101;

    public static final int CONNECTION_CLOSE_OK = 200;
    public static final int CONNECTION_CLOSE_FAILED = 201;

    public static final int PRODUCT_SAVE_OK = 0;
    public static final int PRODUCT_SAVE_FAILED = 1;

    public static final int PURCHASE_SAVE_OK = 500;
    public static final int PURCHASE_SAVE_FAILED = 501;

    public static final int CUSTOMER_SAVE_OK = 300;
    public static final int CUSTOMER_SAVE_FAILED = 301;

    public static final int CANCEL_FAILED = 9001;
    public static final int CANCEL_OK = 90000;

    public int connect(String dbfile);
    public int disconnect();

    public Product loadProduct(int id);
    public int saveProduct(Product model);

    public Customer loadCustomer(int id);
    public int saveCustomer(Customer model);

    //public int loadPurchase(int id);
    public int savePurchase(Purchase purchase);

    //public PurchaseListModel loadPurchaseHistory(int customerID);
    public ProductList searchProduct(String name, double minPrice, double maxPrice);

    public User loadUser(String username);
    public int saveUser(User user);

    public Purchase loadPurchase(int id);
}