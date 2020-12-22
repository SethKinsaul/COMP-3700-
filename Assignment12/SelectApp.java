/*
Seth Kinsaul (smk0036@auburn.edu)
Make a connection to the store's database and print out all rows of the tables
 */
//package

//imports
import java.sql.*;

public class SelectApp {

    private static SelectApp selectApp;

    public static SelectApp getInstance() {
        if (selectApp == null) selectApp = new SelectApp();
        return selectApp;
    }

    private SelectApp() {}

    private Connection connect() {
        Connection conn = null;
        // db parameters
        String url = "jdbc:sqlite:C:/Users/Seth/Desktop/Assignment12/data/store.db";
        try {
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    public String selectProductTable(String productID){
        String sql = "SELECT * FROM Products WHERE ProductID=";
        sql = sql + productID;
        StringBuilder sb = new StringBuilder();
        try (Connection conn = this.connect()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            ResultSetMetaData md = rs.getMetaData();
            int columnCount =  md.getColumnCount();

        while (rs.next()) {
         //System.out.println(rs.getString("ID") + ", " + rs.getString("Name"));
            for (int i = 1; i <= columnCount; i++) {
                sb.append(rs.getString(i) + ", ");
            }
            sb.append("\n");
        }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return sb.toString();
    }
}