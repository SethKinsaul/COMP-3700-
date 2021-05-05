import com.google.gson.Gson;

import java.io.PrintWriter;
import java.net.*;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Server {
    static String dbfile = "C:\\Users\\Seth\\Desktop\\Final Project\\data\\store.db";

    public static void main(String[] args) {

        HashMap<Integer, User> activeUsers = new HashMap<Integer, User>();

        int totalActiveUsers = 0;

        int port = 1000;

        if (args.length > 0) {
            System.out.println("Running arguments: ");
            for (String arg : args)
                System.out.println(arg);
            port = Integer.parseInt(args[0]);
            dbfile = args[1];
        }

        try {
            SqlDataAdapter adapter = new SqlDataAdapter();
            Gson gson = new Gson();
            adapter.connect(dbfile);

            ServerSocket server = new ServerSocket(port);
            System.out.println("Server is listening at port = " + port);

            while (true) {
                Socket pipe = server.accept();
                PrintWriter out = new PrintWriter(pipe.getOutputStream(), true);
                Scanner in = new Scanner(pipe.getInputStream());

                Message msg = gson.fromJson(in.nextLine(), Message.class);

                if (msg.code == Message.GET_PRODUCT) {
                    System.out.println("GET product with id = " + msg.data);
                    Product p = adapter.loadProduct(Integer.parseInt(msg.data));
                    if (p == null) {
                        msg.code = Message.OPERATION_FAILED;
                    }
                    else {
                        msg.code = Message.OPERATION_OK; // load successfully!!!
                        msg.data = gson.toJson(p);
                    }
                    out.println(gson.toJson(msg));
                }

                if (msg.code == Message.PUT_PRODUCT) {
                    Product p = gson.fromJson(msg.data, Product.class);
                    System.out.println("PUT command with Product = " + p);
                    int res = adapter.saveProduct(p);
                    if (res == DatabaseManager.PRODUCT_SAVE_OK) {
                        msg.code = Message.OPERATION_OK;
                    }
                    else {
                        msg.code = Message.OPERATION_FAILED;
                    }
                    out.println(gson.toJson(msg));
                }

                if (msg.code == Message.LOGIN) {
                    User u = gson.fromJson(msg.data, User.class);
                    System.out.println("LOGIN command with User = " + u);
                    User user = adapter.loadUser(u.mUsername);
                    if (user != null && user.mPassword.equals(u.mPassword)) {
                        msg.code = Message.OPERATION_OK;
                        totalActiveUsers++;
                        int accessToken = totalActiveUsers;
                        msg.ssid = accessToken;
                        msg.data = gson.toJson(user, User.class);
                        activeUsers.put(accessToken, user);
                    }
                    else {
                        msg.code = Message.OPERATION_FAILED;
                    }
                    out.println(gson.toJson(msg));  // answer login command!
                }
                if (msg.code == Message.SEARCH_PRODUCT) {
                    String name = "Apple";
                    double min = 0, max = 10000;
                    ProductList res = adapter.searchProduct(name, min, max);
                    msg.code = Message.OPERATION_OK;
                    msg.data = gson.toJson(res);
                    out.println(gson.toJson(msg));  // answer get purchase history!!!
                }


                // add responding to GET_USER, PUT_USER,...
                server.close();
                in.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
