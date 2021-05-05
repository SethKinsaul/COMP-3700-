public class Message {

    public static final int LOGIN = 0;
    public static final int LOGOUT = 1;

    public static final int GET_USER = 10;
    public static final int PUT_USER = 11;

    public static final int GET_PRODUCT = 100;
    public static final int PUT_PRODUCT = 101;

    public static final int GET_CUSTOMER = 200;
    public static final int PUT_CUSTOMER = 201;

    public static final int GET_PURCHASE_LIST = 500;

    public static final int OPERATION_OK = 1000; // server responses!
    public static final int OPERATION_FAILED = 1001;
    public static final int SEARCH_PRODUCT = 2000;

    public int code;
    public int ssid;
    public String data;

    public Message() {
        code = 0;
        data = null;
    }

    public Message(int code, String data) {
        this.code = code;
        this.data = data;
    }
    public Message(int code, int ssid, String data) {
        this.code = code;
        this.ssid = code;
        this.data = data;
    }
}